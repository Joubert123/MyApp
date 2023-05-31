package com.example.myapp

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.myapp.databinding.FragmentEditBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemsViewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}

        println()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the passed item
        val selectedItem = arguments?.getString("selectedItem")

        val username = view.findViewById<EditText>(R.id.name)
        username.text = Editable.Factory.getInstance().newEditable(selectedItem)

        // Add an item click listener to the ListView
        binding.cancel.setOnClickListener {
            val navController = view.findNavController()
            navController.navigate(R.id.listFragment)
        }

        binding.save.setOnClickListener {
            val username = view.findViewById<EditText>(R.id.name).text.toString()
            val email = view.findViewById<EditText>(R.id.add_email).text.toString()

            if (username.isNotEmpty()) {
                itemsViewModel = ItemsViewModel()
                itemsViewModel.addItem(listOf(username, email))
                val navController = view.findNavController()
                navController.navigate(R.id.listFragment)
            } else {
                // Tell user the username may not be empty
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}