package com.apper.theblogservice.repository;


import com.apper.theblogservice.model.Blogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// CrudRepository<Class that represents the table, Datatype ng Id ng table>
@Repository
public interface BloggerRepository extends CrudRepository<Blogger, String> {
//    public boolean findByEmail(String email);
}
