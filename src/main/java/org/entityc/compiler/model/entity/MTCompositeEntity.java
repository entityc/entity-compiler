package org.entityc.compiler.model.entity;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.doc.annotation.*;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.util.ECLog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@ModelClass(type = ModelClassType.ENTITY, description = "Represents a composite entity in your model.")
public class MTCompositeEntity extends MTEntity {

    public static final String ObjectTag = "object";
    public static final String VersionTag = "version";
    public static final String ReleaseTag = "release";
    public static final String BinderTag = "binder";

    Map<String,MTEntity> constituentEntities = new HashMap<>();

    public MTCompositeEntity(ParserRuleContext ctx, MTModule module, String name) {
        super(ctx, module, name);
    }

    public void addConstituentEntity(String tag, MTEntity entity) {
        if (this.constituentEntities.containsKey(tag) ) {
            if (!this.constituentEntities.get(tag).name.equals(entity.name)) {
                ECLog.logFatal("This composite entity " + this.name + " is already associated to a different entity with the " + tag + " tag.");
            } else {
                return;
            }
        }
        this.constituentEntities.put(tag, entity);
    }

    public MTEntity getConstituentEntity(String tag) {
        return constituentEntities.get(tag);
    }

    public boolean hasConstituentEntity(String tag) {
        return constituentEntities.containsKey(tag);
    }

    public MTEntity getAnyConstituentEntity(String tag, HashSet history) {
        MTEntity constituentEntity = constituentEntities.get(tag);
        if (constituentEntity != null) {
            return constituentEntity;
        }
        ECLog.logInfo("getAnyConstituentEntity(" + tag + ") Checking relationships for " + getName() + "...");
        for (MTRelationship relationship : getRelationships()) {
            MTEntity toEntity = relationship.getTo().getEntity();
            if (history.contains(toEntity.getName())) {
                continue; // break infinite recursion
            }
            if (toEntity.hasTag("release:top") && tag.equals(ReleaseTag)) {
                return toEntity;
            }
            if (toEntity == null || !toEntity.isCompositeEntity()) {
                ECLog.logInfo("  Relationship " + relationship.getName() + " to entity " + toEntity.getName() + " is not to a composite entity.");
                continue;
            }
            history.add(toEntity.getName());
            MTEntity compositeToEntity = ((MTCompositeEntity)toEntity).getAnyConstituentEntity(tag, history);
            if (compositeToEntity != null) {
                return compositeToEntity;
            }
        }
        ECLog.logInfo("  Unable to find child constituent entity!");
        return null;
    }

    public MTEntity getAnyConstituentEntity(String tag) {
        return getAnyConstituentEntity(tag, new HashSet());
    }

        public boolean hasAnyConstituentEntity(String tag) {
        return getAnyConstituentEntity(tag, new HashSet()) != null;
    }

    public void addAttribute(String tag, MTAttribute attribute, MTEntity fromEntity) {
        addConstituentEntity(tag, fromEntity);
        MTAttribute constituentAttribute = MTAttribute.Copy(attribute, this);
        super.addAttribute(constituentAttribute);
    }
}
