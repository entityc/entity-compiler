package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.ModelClass;
import org.entityc.compiler.doc.annotation.ModelClassType;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.MTReferenceResolution;
import org.entityc.compiler.model.config.MTSpace;
import org.entityc.compiler.model.entity.MTEntity;
import org.entityc.compiler.model.entity.MTRelationship;
import org.entityc.compiler.model.visitor.MTVisitor;
import org.entityc.compiler.util.ECLog;

@ModelClass(type = ModelClassType.DOMAIN,
    description = "Represents a field (attribute or relationship) associated with the __to__ entity of the relationship.")
public class MTDERelationshipField extends MTNode implements MTNamed, MTDomainBased, MTReferenceResolution  {

    public MTDERelationshipField(ParserRuleContext ctx,  MTDERelationship domainRelationship, String fieldName) {
        super(ctx);
        this.domainRelationship = domainRelationship;
        this.fieldName = fieldName;
    }
    private MTDERelationship domainRelationship;
    private MTDEAttribute fieldAttribute;
    private MTDERelationship fieldRelationship;

    private String fieldName;

    public boolean fieldIsAttribute() {
        return fieldAttribute != null;
    }

    public boolean fieldIsRelationship() {
        return fieldRelationship != null;
    }

    public MTDEAttribute getAttribute() {
        return fieldAttribute;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    @Override
    public boolean resolveReferences(MTSpace space, int pass) {
        if ( fieldIsAttribute() || fieldIsRelationship() ) {
            return false;
        }
        MTRelationship relationship = domainRelationship.getRelationship();
        if (relationship == null) {
            return true;
        }
        MTEntity entity = relationship.getTo() != null && relationship.getTo().getEntity() != null ? relationship.getTo().getEntity() : null;
        if (entity == null) {
            return true;
        }

        if (entity.hasAttributeNamed(fieldName)) {
            this.fieldAttribute = new MTDEAttribute(getParserRuleContext(), domainRelationship.getDomain(), entity.getName(), fieldName);
            this.fieldAttribute.addTagsWithValues(this.getTagsWithValues()); // transfer the tags to it
        } else if (entity.hasRelationshipNamed(fieldName)) {
            ECLog.logFatal(getParserRuleContext(), "Domain relationship fields of type relationship currently not supported.");
        } else {
            ECLog.logFatal(getParserRuleContext(), "Domain relationship " + this.domainRelationship.getName() + " of entity " + entity.getName() + " does not have an attribute or relationship named: " + fieldName);
        }
        return false;
    }

    @Override
    public MTDomain getDomain() {
        return this.domainRelationship.getDomain();
    }

    @Override
    public String getFullname(String delim) {
        return this.domainRelationship.getFullname(delim) + delim + this.fieldName;
    }

    @Override
    public String getName() {
        return this.fieldName;
    }
}
