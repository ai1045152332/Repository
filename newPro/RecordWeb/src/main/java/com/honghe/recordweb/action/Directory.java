package com.honghe.recordweb.action;

import jodd.util.collection.SortedArrayList;

import java.text.CollationKey;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghongbin on 2016/7/13.
 */
public final class Directory {

    private String id = "";
    private String name = "";
    private String typeId = "";
    private List<Directory> directories;

    private List<Map<String, String>> data = new ArrayList<>();

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }
    public List<Map<String, String>> getData() {
        return data;
    }

    public Directory() {
        this("", "");
    }

    public Directory(String id, String name) {
        this.id = id;
        this.name = name;
        this.directories = new SortedArrayList<>(new Comparator<Directory>() {
            private RuleBasedCollator collator = (RuleBasedCollator) Collator
                    .getInstance(java.util.Locale.CHINA);

            @Override
            public int compare(Directory o1, Directory o2) {
                CollationKey c1 = collator.getCollationKey(o1.getName().
                        replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
                        .replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
                        .replaceAll("七", "7").replaceAll("八", "8").replaceAll("九", "9"));
                CollationKey c2 = collator.getCollationKey(o2.getName().
                        replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
                        .replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
                        .replaceAll("七", "7").replaceAll("八", "8").replaceAll("九", "9")
                );
                return collator.compare(c1.getSourceString(), c2.getSourceString());
            }
        });
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(List<Directory> directories) {
        this.directories = directories;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
