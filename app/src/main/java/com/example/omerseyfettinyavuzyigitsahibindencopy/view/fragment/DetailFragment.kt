package com.example.omerseyfettinyavuzyigitsahibindencopy.view.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.omerseyfettinyavuzyigitsahibindencopy.R
import com.example.omerseyfettinyavuzyigitsahibindencopy.databinding.FragmentDetailBinding
import com.example.omerseyfettinyavuzyigitsahibindencopy.util.*
import com.example.omerseyfettinyavuzyigitsahibindencopy.view.activity.MainActivity
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = _binding
    private var picturePhoto: Uri? = null
    private var control5: Boolean = false
    private var control6: Boolean = false
    private var selectedCurrency: String = ""
    private var categoryName: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "İlan Detayları"
        binding?.apply {
            spinnerList(currencySpinner)
            currencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    p3: Long
                ) {
                    val selectedItem = p0?.getItemAtPosition(position).toString()
                    selectedCurrency = selectedItem
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //Bu proje için lazım değil
                }

            }
            profileImageView.setOnClickListener {
                pickImageGallary()
            }
            linkEditText.doOnTextChanged { text, start, before, count ->
                if (!text.isNullOrEmpty()) {
                    approveImageView.show()
                } else {
                    approveImageView.hide()
                }
            }
            approveImageView.setOnClickListener {
                val url = linkEditText.text.toString()
                isWebsiteReachable(url)
                //Ping atma işleminin bitmesai için 1 saniye bekletme
                Handler(Looper.getMainLooper()).postDelayed({
                    requireContext().showToastShort("" + returdMessage)
                }, 1000)
            }
        }
        categoryName = arguments?.getString("categoryName").toString()
        return binding?.root
    }


    var returdMessage: String = ""
    fun isWebsiteReachable(responseUrl: String) {
        Thread(Runnable {
            try {
                val url = responseUrl
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    Log.i("pingcontrol", "${url} Ping başarılı bir şekilde atılabildi")
                    returdMessage = "${url} Ping başarılı bir şekilde atılabildi"
                    control5 = true
                }
                connection.disconnect()
            } catch (e: MalformedURLException) {
                e.printStackTrace()
                Log.i("pingcontrol", "Url formatı uygunsuz(https://www.example.com)")
                returdMessage = "${responseUrl} Url formatı uygunsuz(https://www.example.com)"
                control5 = false
            } catch (e: IOException) {
                e.printStackTrace()
                Log.i("pingcontrol", "${responseUrl} bulunamadı")
                returdMessage = "${responseUrl} bulunamadı"
                control5 = false
            }
        }).start()
    }


    private fun pickImageGallary() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            picturePhoto = data.data
            binding?.profileImageView?.setImageURI(picturePhoto)
            binding?.textView?.text = "Başarılı bir şekilde yüklendi "
            control6 = true
        }
    }

    private fun spinnerList(spinner: Spinner) {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currency_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activityBinding = (activity as MainActivity).binding
        activityBinding.apply {
            menuCategoryNameTextView.text =
                "${activityBinding.menuCategoryNameTextView.text} > ${categoryName}"
        }
    }


    override fun onResume() {
        super.onResume()
        val activityBinding = (activity as MainActivity).binding
        activityBinding.apply {
            backPageImageView.show()
            backPageImageView.setOnClickListener {
                findNavController().navigate(R.id.action_detailFragment_to_brandCategoryFragment)
            }
            this.nextPageImageView.apply {
                this.show()
                binding?.apply {
                    var control: Boolean = false
                    var control2: Boolean = false
                    var control3: Boolean = false
                    var control4: Boolean = false
                    nextPageImageView.setOnClickListener {
                        if (nameEditText.textLength() == 0) {
                            nameInputLayout.isErrorEnabled = true
                            nameInputLayout.helperText = "Gerekli*"
                            control = false
                        } else {
                            nameInputLayout.isErrorEnabled = false
                            nameInputLayout.helperText = null
                            control = true
                        }
                        if (linkEditText.textLength() == 0) {
                            linkInputLayout.isErrorEnabled = true
                            linkInputLayout.helperText = "Gerekli*"
                            control3 = false
                        } else {
                            linkInputLayout.isErrorEnabled = false
                            linkInputLayout.helperText = null
                            control3 = true
                        }
                        if (priceEditText.textLength() == 0) {
                            priceInputLayout.isErrorEnabled = true
                            priceInputLayout.helperText = "Gerekli*"
                            control4 = false
                        } else {
                            priceInputLayout.isErrorEnabled = false
                            priceInputLayout.helperText = null
                            control4 = true
                        }
                        if (descriptionEditText.textLength() < 50) {
                            descriptionTextInputLayout.isErrorEnabled = true
                            descriptionTextInputLayout.helperText =
                                "Minumum 50 karakterli olmalıdır*"
                            control2 = false
                        } else if (descriptionEditText.textLength() > 2000) {
                            descriptionTextInputLayout.isErrorEnabled = true
                            descriptionTextInputLayout.helperText =
                                "Maksimum 2000 karakterli olmalıdır*"
                            control2 = false
                        } else {
                            descriptionTextInputLayout.isErrorEnabled = false
                            descriptionTextInputLayout.helperText = null
                            control2 = true
                        }
                        if (control == true && control2 == true && control3 == true && control4 == true && control6 == true) {
                            //requireContext().showToastShort("basarili")
                            val navController = findNavController()
                            val myBundle = bundleOf(
                                "advertName" to nameEditText.textString(),
                                "advertLink" to linkEditText.textString(),
                                "advertPrice" to priceEditText.textString(),
                                "advertDescription" to descriptionEditText.textString(),
                                "advertCurrency" to selectedCurrency,
                                "advertPicture" to picturePhoto
                            )
                            navController.navigate(
                                R.id.action_detailFragment_to_previewAdvertFragment,
                                myBundle
                            )
                        }
                    }
                }
            }
        }
        activityBinding.progressBar.progress = 2
    }

}