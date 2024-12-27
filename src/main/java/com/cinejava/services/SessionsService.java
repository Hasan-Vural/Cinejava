package com.cinejava.services;

import com.cinejava.constants.DataStoreConstants;
import com.cinejava.models.Session;

public class SessionsService extends GenericService<Session> {
    public SessionsService() {
        super(Session.class, DataStoreConstants.SESSION_STORE_NAME);
    }
}