package com.ebartmedia.Model;

import com.google.gson.annotations.SerializedName;

public class Categories {


  //  @SerializedName("categoryName")
    @SerializedName("catName")
 //   private String categoryName;
    private String catName;
 //   @SerializedName("subCategoryName")
    @SerializedName("subCatName")
 //   private String subCategoryName;
    private String subCatName;

 //   public Categories(String categoryName, String subCategoryName) {
    public Categories(String catName, String subCatName) {

        this.catName = catName;
        this.subCatName = subCatName;

    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }
}
