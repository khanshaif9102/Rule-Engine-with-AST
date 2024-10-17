package com.shaif.zeotap.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shaif.zeotap.model.Node;
import com.shaif.zeotap.repository.RuleRepository;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    // Dynamic rule parsing from rule string
    public Node createRule(String ruleString) {
        // Parse the rule string into an AST
        return parseRule(ruleString);
    }

    // Combining two rules with the given operator
    public Node combineRules(Node rule1, Node rule2, String operator) {
        Node combined = new Node();
        combined.setType(operator);  // Can be "AND" or "OR"
        combined.setLeft(rule1);
        combined.setRight(rule2);
        return ruleRepository.save(combined);
    }

    public boolean evaluateRule(Node node, Map<String, Object> data) {
        // Recursively evaluate the AST based on input data
        if (node.getType().equals("operand")) {
            return evaluateCondition(node.getValue(), data);
        }
        boolean leftResult = evaluateRule(node.getLeft(), data);
        boolean rightResult = evaluateRule(node.getRight(), data);
        return node.getType().equals("AND") ? leftResult && rightResult : leftResult || rightResult;
    }

    private boolean evaluateCondition(String condition, Map<String, Object> data) {
        // Parsing and evaluating simple conditions
        if (condition.contains(">")) {
            String[] parts = condition.split(">");
            String field = parts[0].trim();
            int value = Integer.parseInt(parts[1].trim());
            return (int) data.get(field) > value;
        } else if (condition.contains("=")) {
            String[] parts = condition.split("=");
            String field = parts[0].trim();
            String value = parts[1].trim().replace("'", "");
            return data.get(field).equals(value);
        } else if (condition.contains("<")) {
            String[] parts = condition.split("<");
            String field = parts[0].trim();
            int value = Integer.parseInt(parts[1].trim());
            return (int) data.get(field) < value;
        }
        return false;
    }

    private Node parseRule(String ruleString) {
        // Trim leading/trailing whitespace
        ruleString = ruleString.trim();

        // Remove leading/trailing parentheses, if present
        if (ruleString.startsWith("(") && ruleString.endsWith(")")) {
            ruleString = ruleString.substring(1, ruleString.length() - 1).trim();
        }

        // Check for AND or OR to create operator nodes
        if (ruleString.contains(" AND ")) {
            String[] parts = ruleString.split(" AND ", 2); // Split by AND
            Node root = new Node();
            root.setType("AND");
            root.setLeft(parseRule(parts[0].trim()));  // Parse the left part
            root.setRight(parseRule(parts[1].trim())); // Parse the right part
            return root;
        } else if (ruleString.contains(" OR ")) {
            String[] parts = ruleString.split(" OR ", 2); // Split by OR
            Node root = new Node();
            root.setType("OR");
            root.setLeft(parseRule(parts[0].trim()));  // Parse the left part
            root.setRight(parseRule(parts[1].trim())); // Parse the right part
            return root;
        } else {
            // Operand node, remove extra quotes if present
            Node leaf = new Node();
            leaf.setType("operand");
            leaf.setValue(ruleString.replace("\"", "").trim());  // Clean quotes
            return leaf;
        }
    }

}
