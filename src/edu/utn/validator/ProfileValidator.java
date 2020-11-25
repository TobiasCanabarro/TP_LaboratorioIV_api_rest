package edu.utn.validator;

import edu.utn.entity.User;

public class ProfileValidator extends Validator {

    public boolean isValid (User userWithChange, User userWithoutChange){

        boolean value = !userWithChange.getName().equals(userWithoutChange.getName());
        value |=  !userWithChange.getSurname().equals(userWithoutChange.getSurname());
        value |= !userWithChange.getNickname().equals(userWithoutChange.getNickname());
        value |= !userWithChange.getEmail().equals(userWithoutChange.getEmail());
        value |= !userWithChange.getBirthday().equals(userWithoutChange.getBirthday());

        return value;
    }
}
