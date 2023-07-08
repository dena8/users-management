package com.example.usersmanagement.Services;

import com.example.usersmanagement.Models.Entities.UserEntity;
import com.example.usersmanagement.Models.Repositories.UsersRepository;
import com.example.usersmanagement.Services.Interfaces.UserCriteriaService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCriteriaServiceImpl implements UserCriteriaService {
    private final UsersRepository usersRepository;

    public UserCriteriaServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UserEntity> findOrderUsers(String firstName) {
        return usersRepository.findAll((Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Sort sortByLastNameAndBirthDate = Sort.by(Sort.Direction.ASC, "lastName", "birthDate");

            if (firstName != null) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
            }

            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

            query.orderBy(QueryUtils.toOrders(sortByLastNameAndBirthDate, root, criteriaBuilder));

            return query.getRestriction();
        });
    }
}
