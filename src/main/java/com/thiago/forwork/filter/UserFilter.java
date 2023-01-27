package com.thiago.forwork.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFilter extends PageQueryFilter {
    LocalDate start;
    LocalDate end;
    String name;
}
