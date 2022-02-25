/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.model.domain;

import org.antlr.v4.runtime.ParserRuleContext;
import org.entityc.compiler.model.MTNode;
import org.entityc.compiler.model.visitor.MTVisitor;

/**
 * Represents the notation to cache something in a domain
 */
public class MTDCache extends MTNode {

    private CacheType type;
    private int       size;
    private float     loadFactor;

    public MTDCache(ParserRuleContext ctx) {
        super(ctx);
    }

    public CacheType getType() {
        return type;
    }

    public void setType(CacheType type) {
        this.type = type;
    }

    public boolean isLRUType() {
        return this.type == CacheType.LRU;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getLoadFactor() {
        return loadFactor;
    }

    public void setLoadFactor(float loadFactor) {
        this.loadFactor = loadFactor;
    }

    @Override
    public void accept(MTVisitor visitor) {

    }

    public enum CacheType {
        LRU("lru"),
        ;

        String name;

        CacheType(String name) {
            this.name = name;
        }

        public static CacheType FromName(String name) {
            for (CacheType t : values()) {
                if (t.getName().equals(name)) {
                    return t;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }
    }
}
