package org.entityc.compiler.model.entity;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTModule;
import org.entityc.compiler.util.ECLog;

import java.util.HashMap;
import java.util.Map;

public class MTCompositeEntity extends MTEntity {

    public static final String ObjectTag = "object";
    public static final String VersionTag = "version";
    public static final String ReleaseTag = "release";

    Map<String,MTEntity> constituentEntities = new HashMap<>();

    public MTCompositeEntity(ParserRuleContext ctx, MTModule module, String name) {
        super(ctx, module, name);
    }

    private void addConstituentEntity(String tag, MTEntity entity) {
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

    public void addAttribute(String tag, MTAttribute attribute, MTEntity fromEntity) {
        addConstituentEntity(tag, fromEntity);
        MTAttribute constituentAttribute = MTAttribute.Copy(attribute, this);
        super.addAttribute(constituentAttribute);
    }
}
