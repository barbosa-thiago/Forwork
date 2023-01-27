package com.thiago.forwork.repository.specification;

import com.thiago.forwork.model.Task;
import com.thiago.forwork.model.User;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

public class UserSpec {

    public static Specification<User> name(String name) {
        return (root, query, cb) -> ofNullable(name).map(val ->
            cb.like(root.get("name"), "%" + name + "%")).orElse(null);
    }

    public static Specification<User> period(LocalDate start, LocalDate end) {
        if(isNull(start) || isNull(end)) return null;

        return (root, query, cb) ->{

            var taskSubquery = query.subquery(Task.class);
            var taskRoot = taskSubquery.from(Task.class);

            Predicate taskBetween = cb.between(taskRoot.get("deadline"), start, end);
            Predicate asignee = cb.equal(taskRoot.get("asignee"), root);

            taskSubquery.select(taskRoot).where(taskBetween, asignee);
            return cb.exists(taskSubquery);
        };
    }


}
