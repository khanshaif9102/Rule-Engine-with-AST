package com.shaif.zeotap.dto;

import com.shaif.zeotap.model.Node;

public class CombineRuleRequest {
    private Node rule1;
    private Node rule2;

    public Node getRule1() {
        return rule1;
    }

    public void setRule1(Node rule1) {
        this.rule1 = rule1;
    }

    public Node getRule2() {
        return rule2;
    }

    public void setRule2(Node rule2) {
        this.rule2 = rule2;
    }
}
