//package com.example.demo.util;
//
//import org.apache.http.conn.ssl.SSLContextBuilder;
//import org.apache.http.conn.ssl.TrustStrategy;
//
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.TrustManagerFactory;
//import javax.net.ssl.X509TrustManager;
//import java.security.KeyStore;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//
//public class HttpClientUtil {
//    //参数为信任的证书，信任策略
//    public SSLContextBuilder loadTrustMaterial(KeyStore truststore, TrustStrategy trustStrategy) throws NoSuchAlgorithmException, KeyStoreException {
//        TrustManagerFactory tmfactory = TrustManagerFactory.getInstance(this.trustManagerFactoryAlgorithm == null ? TrustManagerFactory.getDefaultAlgorithm() : this.trustManagerFactoryAlgorithm);
//        tmfactory.init(truststore);
//        TrustManager[] tms = tmfactory.getTrustManagers();
//        if (tms != null) {
//            if (trustStrategy != null) {
//                for(int i = 0; i < tms.length; ++i) {
//                    TrustManager tm = tms[i];
//                    if (tm instanceof X509TrustManager) {
//                        tms[i] = new SSLContextBuilder.TrustManagerDelegate((X509TrustManager)tm, trustStrategy);
//                    }
//                }
//            }
//
//            TrustManager[] arr$ = tms;
//            int len$ = tms.length;
//
//            for(int i$ = 0; i$ < len$; ++i$) {
//                TrustManager tm = arr$[i$];
//                this.trustManagers.add(tm);
//            }
//        }
//
//        return this;
//    }
//    //重载的方法，不需要证书，只需要信任策略
//    public SSLContextBuilder loadTrustMaterial(TrustStrategy trustStrategy) throws NoSuchAlgorithmException, KeyStoreException {
//        return this.loadTrustMaterial((KeyStore)null, (TrustStrategy)trustStrategy);
//    }
//}
