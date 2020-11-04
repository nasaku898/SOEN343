package com.soen343.shs.dal.service;

import com.soen343.shs.dal.service.exceptions.IllegalStateException;
import com.soen343.shs.dal.service.exceptions.state.SHSSameStateException;

public class ErrorHelper {

    static void checkForSameStateException(final boolean desiredState, final boolean currentState, final String sameStateExceptionErrorMessage) {
        if (desiredState && currentState || (!desiredState && !currentState)) {
            throw new SHSSameStateException(sameStateExceptionErrorMessage);
        }
    }

    /**
     * @param id        id of the object
     * @param classType class type of object
     * @param <Entity>  class of object
     * @return new string formatted to return our error message
     */
    static <Entity> String getSHSNotFoundErrorMessage(final long id, final Class<Entity> classType) {
        return String.format("Error: %s with ID: %d does not exist. Please enter a valid %s id.", classType.getName(), id, classType.getName());
    }

    /**
     * @param id        id of the object
     * @param classType class type of object
     * @param <Entity>  class of object
     * @return new string formatted to return our error message
     */
    static <Entity> String getSameStateExceptionErrorMessage(final Class<Entity> classType, final long id) {
        return String.format("Error: object: %s with id: %d is already in the expected state", classType.getName(), id);
    }

    /**
     * @param classType
     * @param id
     * @param stateToCheck
     * @param <Entity>
     */
    static <Entity> void checkForIllegalStateException(final Class<Entity> classType, final long id, final boolean stateToCheck) {
        final String ERROR_MSG = String.format("Error: object: %s with id: %d cannot be opened since it is blocked.", classType.getName(), id);
        if (stateToCheck) {
            throw new IllegalStateException(String.format(ERROR_MSG, id));
        }
    }
}
