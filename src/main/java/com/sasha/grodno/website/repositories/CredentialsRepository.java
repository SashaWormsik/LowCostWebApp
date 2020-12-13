package com.sasha.grodno.website.repositories;

import com.sasha.grodno.website.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, Integer> {
}
