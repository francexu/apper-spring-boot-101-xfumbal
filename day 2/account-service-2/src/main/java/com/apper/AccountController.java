package com.apper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        Account account= accountService.create(request.getFirstName(), request.getLastname(), request.getEmail(), request.getPassword());

        CreateAccountResponse response = new CreateAccountResponse();
        response.setVerificationCode(account.getVerificationCode());

        return response;
    }

    @GetMapping("{accountId}")
    public GetAccountResponse getAccount(@PathVariable String accountId) {
        Account account = accountService.get(accountId);

        return createGetAccountResponse(account);
    }

    @GetMapping
    public List<GetAccountResponse> getAllAccounts() {
        List<GetAccountResponse> responseList =
                new ArrayList<>();

        for (Account account : accountService.getAll()) {
            GetAccountResponse response = createGetAccountResponse(account);
            responseList.add(response);
        }

        return responseList;
    }

    // Laboratory exercises

    @PutMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateAccountResponse updateAccount(@PathVariable String accountId, @RequestBody CreateAccountRequest request) {
        accountService.update(accountId, request.getFirstName(), request.getLastname(), request.getEmail(), request.getPassword());

        UpdateAccountResponse response = new UpdateAccountResponse();
        response.setLastUpdated(LocalDateTime.now());

        return response;
    }


    @DeleteMapping("{accoundId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable String accountId) {
        accountService.delete(accountId);
    }

    private GetAccountResponse createGetAccountResponse(Account account) {
        GetAccountResponse response = new GetAccountResponse();
        response.setBalance(account.getBalance());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setUsername(account.getUsername());
        response.setRegistrationDate(account.getCreationDate());

        return response;
    }
}
