package com.example.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.myapp.databinding.FragmentListBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemsViewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsViewModel = ItemsViewModel()

        // Setup the ArrayAdapter
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemsViewModel.getAll())
        binding.eventsListData.adapter = arrayAdapter

        // Add an item click listener to the ListView
        binding.eventsListData.setOnItemClickListener { parent, view, position, id ->
            // The item at the clicked position
            val selectedItem = parent.getItemAtPosition(position) as String
            //val action = ListFragment.actionListFragmentToDetailFragment(selectedItem)
            // Create a bundle to hold the selected item
            val bundle = bundleOf("selectedItem" to selectedItem)

            val navController = view.findNavController()
            navController.navigate(R.id.detailFragment, bundle)

            // Show a simple toast message
//            Toast.makeText(requireContext(), "Clicked: $selectedItem", Toast.LENGTH_SHORT).show()
        }

        binding.addNewUser.setOnClickListener {
            val navController = view.findNavController()
            navController.navigate(R.id.addFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply { }
            }
    }
}