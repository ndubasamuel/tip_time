package com.example.tiptime.network.Models

import okhttp3.CertificatePinner

object SslPinning {
    fun getPinnedCertificate(): CertificatePinner {
        return CertificatePinner.Builder()
            .add("sha256/bd687a11993243d4c3774b91cba00717f5041888")
            .build()
    }
}