package com.example.myapp

import android.content.ClipData
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapp.databinding.FragmentDetailBinding
import com.example.myapp.databinding.FragmentListBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemsViewModel: ItemsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val binding = binding!!
        val view = binding.root

//        binding.itemsViewModel = itemsViewModel
        binding.attendeesList
        binding.lifecycleOwner = viewLifecycleOwner

        val navController = findNavController()

        val args: DetailFragmentArgs by navArgs()
        val selectedItem = args.selectedItem

        itemsViewModel.setCurrentItem(selectedItem)

        itemsViewModel.name.observe(viewLifecycleOwner, { name ->
            binding.eventTitle.text = Editable.Factory.getInstance().newEditable(name)
        })
        itemsViewModel.date.observe(viewLifecycleOwner, { date ->
            binding.eventDate.text = Editable.Factory.getInstance().newEditable(date)
        })
        itemsViewModel.location.observe(viewLifecycleOwner, { location ->
            binding.eventLocation.text = Editable.Factory.getInstance().newEditable(location)
        })

        val editButton = binding.editAttendee
        val backToListButton = binding.back

        editButton.setOnClickListener() {
            binding.dividerDetailPage.setVisibility(View.VISIBILE)
            binding.detailEditTextView.setVisibility(View.VISIBILE)

            binding.editTextName.setVisibility(View.VISIBLE)
            binding.editTextName.setText(itemsViewModel.name.value.toString())

            binding.editTextDate.setVisibility(VISIBILE)
            binding.editTextDate.setText(itemsViewModel.date.value.toString())

            binding.editTextEmail.setVisibility(VISIBILE)
            binding.editTextEmail.setText(itemsViewModel.email.value.toString())

            binding.editTextLocation.setVisibility(VISIBILE)
            binding.editTextLocation.setText(itemsViewModel.location.value.toString())


        }
        binding.saveChangesButton.setVisibility(VISIBLE)
        binding.cancelChangesButton.setVisibility(VISIBLE)

        saveButton.setOnClickListener() {
            val v = Snackbar.make(
                    saveButton2, "Do you want to save these changes?"
                    BaseTransientBottomBar.LENGTH_INDEFINITE
                )
                    .setBackgroundTint(Color.BLUE)
                    .setTextColor(Color.WHITE)
                    .setAction("Yes"){
                        val id = binding.idTextView.text.toString()
                        val itemName = binding.editTextName.text.toString()
                        val itemDate = binding.editTextDate.text.toString()
                        val itemEmail = binding.editTextEmail.text.toString()
                        val itemLocation = binding.editTextLocation.text.toString()


                        itemViewModel.updateitem(
                            ClipData.Item(
                                id,
                                itemName,
                                itemDate,
                                itemEmail,
                                itemLocation
                            )
                        )
                        binding.titleEditTextView.setVisitbility(View.INVISIBLE)
                        binding.editTextName.setVisitbility(INVISIBLE)
                        binding.editTextDate.setVisitbility(INVISIBLE)
                        binding.editTextEmail.setVisitbility(INVISIBLE)
                        binding.editTextLocation.setVisitbility(INVISIBLE)
                        binding.dividerDetailPage.setVisitbility(INVISIBLE)
                        binding.saveChangesButton.setVisitbility(INVISIBLE)
                        binding.cancelChangesButton.setVisitbility(INVISIBLE)

                    }
        }
            .show()
        return view
    }



            cancelButton2.setOnClickListener() {
                binding.titleEditTextView.setVisitbility(INVISIBLE)
                binding.editTextName.setVisitbility(INVISIBLE)
                binding.editTextDate.setVisitbility(INVISIBLE)
                binding.editTextEmail.setVisitbility(INVISIBLE)
                binding.editTextLocation.setVisitbility(INVISIBLE)
                binding.dividerDetailPage.setVisitbility(INVISIBLE)
                binding.saveChangesButton.setVisitbility(INVISIBLE)
                binding.cancelChangesButton.setVisitbility(INVISIBLE)
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the passed item
        val selectedItem = arguments?.getString("selectedItem")

        val title = view.findViewById<TextView>(R.id.event_title)

        title.text = selectedItem

        // Add an item click listener to the ListView
        binding.editAttendee.setOnClickListener {

            // Create a bundle to hold the selected item
            val bundle = bundleOf("selectedItem" to selectedItem)


            val navController = view.findNavController()
            navController.navigate(R.id.editFragment, bundle)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}