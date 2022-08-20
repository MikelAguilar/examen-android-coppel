package com.miguelaguilar.superherocodechallengecoppel.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miguelaguilar.superherocodechallengecoppel.data.network.getMD5Hash
import com.miguelaguilar.superherocodechallengecoppel.databinding.FragmentWebViewBinding
import com.orhanobut.hawk.Hawk

class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding
    private var webLink: String = ""
    private var webLinkBuilded : String = ""
    private var ts : String = (System.currentTimeMillis()/1000).toString()
    private val PRIVATE_KEY = "18f75c5969e5e5ace434b60614d5fda35d62231e"
    private val API_KEY = "b690dd727c45072137df09793f454127"
    private val TS_BUILDED = "ts=${ts}"
    private val API_KEY_BUILDED = "apikey=${API_KEY}"
    private val HASH_BUILDED ="hash=${getMD5Hash(ts,PRIVATE_KEY, API_KEY)}"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(layoutInflater)
        webLink = arguments?.getString("webLink") ?: ""

        configWB()
        webLinkBuilded = "${webLink}?${TS_BUILDED}&${API_KEY_BUILDED}&${HASH_BUILDED}"
        binding.webView.loadUrl(webLinkBuilded)

        return binding.root
    }

    fun configWB(){
        binding.webView.settings.javaScriptEnabled = true
    }
}