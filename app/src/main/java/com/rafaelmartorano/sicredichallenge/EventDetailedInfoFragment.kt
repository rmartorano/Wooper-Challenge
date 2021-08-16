package com.rafaelmartorano.sicredichallenge

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.JsonElement
import com.rafaelmartorano.sicredichallenge.data.model.CheckinPost
import com.rafaelmartorano.sicredichallenge.data.model.EventItem
import com.rafaelmartorano.sicredichallenge.databinding.FragmentEventDetailedInfoBinding
import com.rafaelmartorano.sicredichallenge.presentation.viewmodel.EventViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDetailedInfoFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentEventDetailedInfoBinding
    private lateinit var event: EventItem
    private lateinit var viewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_detailed_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEventDetailedInfoBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        val args: EventDetailedInfoFragmentArgs by navArgs()
        event = args.selectedEvent
        binding.apply {
            tvTitle.text = event.title
            tvPrice.text = "R$ ${event.price}"
            tvDate.text = event.formattedDate
            tvDescription.text = event.description
            Glide.with(ivImage.context)
                .load(event.image)
                .placeholder(R.drawable.defaultimage)
                .into(ivImage)
            buttonCheckin.setOnClickListener { createCheckInDialog() }
            mapView.onCreate(savedInstanceState)
            mapView.getMapAsync(this@EventDetailedInfoFragment)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val location = LatLng(event.latitude, event.longitude)
        googleMap.addMarker(
            MarkerOptions()
                .position(location)
                .title(event.title)
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16f))
        binding.mapView.onResume()
        Log.i("Teste", "should move camera")
    }

    private fun createCheckInDialog() {
        val dialogView = LayoutInflater.from(activity).inflate(R.layout.custom_dialog, null)
        val titleTv: TextView = dialogView.findViewById(R.id.tvTitleCheckin)
        val nameInput: EditText = dialogView.findViewById(R.id.etNameChekin)
        val email: EditText = dialogView.findViewById(R.id.etEmailCheckin)
        val dialog = AlertDialog.Builder(activity).setView(dialogView).show()
        titleTv.text = event.title
        val btnCheckinDialog: Button = dialogView.findViewById(R.id.btnCheckinDialog)

        btnCheckinDialog.setOnClickListener {
            checkinEvent(
                nameInput.text.toString(),
                email.text.toString(),
                dialog
            )
        }

    }


    private fun openErrorDialog(nameInput: String, email: String) {
        val errorDialogView =
            LayoutInflater.from(activity).inflate(R.layout.checking_error_dialog, null)
        val dialog = AlertDialog.Builder(activity).setView(errorDialogView).show()
        val btnCancel: Button = errorDialogView.findViewById(R.id.buttonCancelErrorDialog)
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        val btnTryAgain: Button = errorDialogView.findViewById(R.id.buttonTryAgain)
        btnTryAgain.setOnClickListener(View.OnClickListener {
            checkinEvent(
                nameInput,
                email,
                dialog
            )
        })
    }

    private fun checkinEvent(nameInput: String, email: String, dialog: AlertDialog) {
        if (nameInput.isBlank())
            Toast.makeText(context, "Digite seu nome", Toast.LENGTH_SHORT).show()
        else if (!Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() || email.isBlank()
        )
            Toast.makeText(context, "Digite um email válido", Toast.LENGTH_SHORT)
                .show()
        else {
            CoroutineScope(Dispatchers.IO).launch {
                val response = viewModel.checkinEvent(
                    CheckinPost(
                        event.id,
                        nameInput,
                        email
                    )
                )
                withContext(Dispatchers.Main) {
                    dialog.dismiss()
                    if (response.isSuccessful) {
                        openSuccessfulDialog()
                    } else {
                        Log.d("Error", "erro no post: ${response.message()}")
                        openErrorDialog(nameInput, email)
                    }
                }

            }
        }
    }

    private fun openSuccessfulDialog() {
        val dialogBuilder = AlertDialog.Builder(context)
        val layoutView = layoutInflater.inflate(R.layout.dialog_success_layout, null)
        val dialogButton = layoutView.findViewById(R.id.btnDialog) as Button
        dialogBuilder.setView(layoutView)
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
        dialogButton.setOnClickListener { alertDialog.dismiss() }
    }

}