package org.entityc.compiler.model;

import org.entityc.compiler.model.domain.MTNamed;
import org.entityc.compiler.model.entity.MTTemplateSupport;
import org.entityc.compiler.model.visitor.MTVisitor;

public class MTRealm extends MTNode implements MTTemplateSupport, MTNamed {

    private final String name;

    public MTRealm(String name) {
        this.name = name;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public String getName() {
        return this.name;
    }
}
