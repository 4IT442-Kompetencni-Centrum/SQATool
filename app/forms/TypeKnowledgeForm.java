package forms;


import daos.impl.DAOs;
import models.TypeKnowledge;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

public class TypeKnowledgeForm {

    private List<Map<String, String>> knowledgeTypes;


    public List<Map<String, String>> getKnowledgeTypes() {
        return knowledgeTypes;
    }

    public TypeKnowledgeForm setKnowledgeTypes(List<Map<String, String>> knowledgeTypes) {
        this.knowledgeTypes = knowledgeTypes;
        return this;
    }

    public TypeKnowledgeForm() {
    }

    public TypeKnowledgeForm(List<TypeKnowledge> list) {
        for (TypeKnowledge typeKnowledge : list) {
            HashMap<String, String> item = new HashMap<>();
            item.put("id", typeKnowledge.getTypeKnowledgeId().toString());
            item.put("value", typeKnowledge.getValue());
            knowledgeTypes.add(item);
        }
    }


    public List<TypeKnowledge> getList() {
        List<TypeKnowledge> list = new LinkedList<>();

        if (this.getKnowledgeTypes() == null) {
            return list;
        }

        for (Map<String, String> map : this.getKnowledgeTypes()) {
            TypeKnowledge typeKnowledge = null;

            if (map.get("value") == null || map.get("value").equals("")) {
                continue;
            }

            if (map.get("id") != null && !map.get("id").equals("")) {
                typeKnowledge = DAOs.getTypeKnowledgeDao().findById(Long.parseLong(map.get("id")));
            }

            if (typeKnowledge == null) {
                typeKnowledge = new TypeKnowledge();
            }

            typeKnowledge.setValue(map.get("value"));

            list.add(typeKnowledge);
        }

        return list;
    }
}
