package io.micro_texto.adapters.out.audit;

import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object entity) {
        CustomRevisionEntity revisionEntity = (CustomRevisionEntity) entity;
        revisionEntity.setUsuario("usuário faz de conta"); // Precisa do Spring Security para encontrá-lo
    }
}

