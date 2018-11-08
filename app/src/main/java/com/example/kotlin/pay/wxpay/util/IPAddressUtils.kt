package com.example.kotlin.pay.wxpay.util

import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException


object IPAddressUtils {

    val ipAddress: String
        get() {
            try {
                val enNetI = NetworkInterface.getNetworkInterfaces()
                while (enNetI.hasMoreElements()) {
                    val netI = enNetI.nextElement()
                    val enumIpAddr = netI.getInetAddresses()
                    while (enumIpAddr.hasMoreElements()) {
                        val inetAddress = enumIpAddr.nextElement()
                        if (inetAddress is Inet4Address && !inetAddress.isLoopbackAddress()) {
                            return inetAddress.getHostAddress()
                        }
                    }
                }
            } catch (e: SocketException) {
                e.printStackTrace()
            }

            return ""
        }

}
