package com.sparta.trafficriskapp.model.repository;

import com.sparta.trafficriskapp.model.entity.Crash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrashRepository extends JpaRepository<Crash, String> {
}