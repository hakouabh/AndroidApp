package com.example.roomapplication.Model;

public class DataContract {
    public static final String TAG = "ServiceHandler";
    public static final int SYNC_STATUS_DONE = 1;
    public static final int SYNC_STATUS_FAILED = 0;
    public static final int RESULT_LOAD_IMAGE = 1000;

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    public static final int ADD_PRODUCT_REQUEST = 3;
    public static final int EDIT_PRODUCT_REQUEST = 4;

    public static final String EXTRA_SYNC = "com.example.roomapplication.EXTRA_SYNC";

    public static final String EXTRA_ID = "com.example.roomapplication.EXTRA_ID";//note
    public static final String EXTRA_TITLE = "com.example.roomapplication.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.roomapplication.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.roomapplication.EXTRA_PRIORITY";

    public static final String EXTRA_PRODUCT_ID = "com.example.roomapplication.EXTRA_PRODUCT_ID";
    public static final String EXTRA_NAME = "com.example.roomapplication.EXTRA_NAME";
    public static final String EXTRA_SKU = "com.example.roomapplication.EXTRA_SKU";
    public static final String EXTRA_QUANTITY = "com.example.roomapplication.EXTRA_QUANTITY";
    public static final String EXTRA_PRICE = "com.example.roomapplication.EXTRA_PRICE";
    public static final String EXTRA_SELLING_PRICE = "com.example.roomapplication.EXTRA_SELLING_PRICE";
}
