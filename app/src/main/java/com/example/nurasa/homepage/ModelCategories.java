package com.example.nurasa.homepage;

public class ModelCategories {
    private String imageResource; // Menyimpan URL gambar ikon dari Firebase Storage
    private String name;

    public ModelCategories() {
        // Default constructor required for calls to DataSnapshot.getValue(ModelCategories.class)
    }

    public ModelCategories(String imageResource, String name) {
        this.imageResource = imageResource;
        this.name = name;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
