package com.trendyol;

public enum UrlFactory {

    BASE_URL("https://www.trendyol.com/"),
    LOGIN_PAGE_URL(BASE_URL, "giris?cb=https%3A%2F%2Fwww.trendyol.com%2F"),
    FORGOT_PASSWORD(BASE_URL, "sifremiunuttum"),
    BOUTIQUE_LIST_PAGE(BASE_URL, "butik/liste/1/kadin");



    public final  String pageUrl;
    UrlFactory(String pageUrl)
    {
        this.pageUrl = pageUrl;
    }
    UrlFactory(UrlFactory baseUrl, String pageUrl)
    {
        this.pageUrl = baseUrl.pageUrl + pageUrl;
    }

}

