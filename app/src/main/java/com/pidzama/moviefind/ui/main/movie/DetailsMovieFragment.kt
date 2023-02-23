package com.pidzama.moviefind.ui.main.movie

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pidzama.moviefind.R
import com.pidzama.moviefind.data.model.cast.CastItem
import com.pidzama.moviefind.data.model.seasons.SeasonsItem
import com.pidzama.moviefind.databinding.FragmentDetailsBinding
import com.pidzama.moviefind.ui.main.cast.adapter.CastAdapter
import com.pidzama.moviefind.ui.main.seasons.adapter.SeasonsAdapter
import com.pidzama.moviefind.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsMovieFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonAddFavorite.setOnClickListener {

        }

        binding.buttonDownload.setOnClickListener {
            showAlertDialog()
        }

        binding.buttonWebSite.setOnClickListener {
            val address: Uri = Uri.parse(args.officialSite)
            val linkIntent = Intent(Intent.ACTION_VIEW, address)
            startActivity(linkIntent)
        }

        binding.buttonShare.setOnClickListener {
            val sendLink = args.urlShow
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, sendLink)
            val chosenIntent = Intent.createChooser(intent, resources.getText(R.string.choose_app))
            startActivity(chosenIntent)
        }

        viewModel.run {
            currentMovie.observe(viewLifecycleOwner) {
                Glide.with(requireContext())
                    .load(it.image?.medium)
                    .centerCrop()
                    .into(binding.imageMovie)

                Glide.with(requireContext())
                    .load(it.image?.original)
                    .centerCrop()
                    .into(binding.imageBackgroundMovie)
                binding.rating.text = it.rating.average.toFloat().toString()
                binding.textDescription.text =
                    Html.fromHtml(it.summary, Html.FROM_HTML_MODE_COMPACT)
                binding.genreMovie.text = it.genres.take(2).toString()
                binding.titleMovie.text = it.name
                binding.premier.text = it.premiered
                binding.movieRatingImage.rating = it.rating.average.toFloat() / 2
                setColorStatus()
                binding.statusMovie.text = it.status
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.listSeasonsCurrentMovie.collect {
                setListSeasons(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.listCurrentMovieCast.collect {
                setListCast(it)
            }
        }

        viewModel.getMovie(args.idMovie)
        viewModel.getSeasonsMovie(args.idMovie)
        viewModel.getCastMovie(args.idMovie)
    }

    private fun setListSeasons(list: ArrayList<SeasonsItem>) {
        binding.recyclerSeasons.run {
            if (adapter == null) {
                adapter = SeasonsAdapter {
                    findNavController().navigate(
                        DetailsMovieFragmentDirections
                            .actionDetailsFragmentToDetailSeasonFragment(
                                it.number,
                                it.image?.medium,
                                it.id
                            )
                    )
                }
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
            (adapter as? SeasonsAdapter)?.submitList(list)
        }
    }

    private fun setListCast(list: ArrayList<CastItem>) {
        binding.recyclerCast.run {
            if (adapter == null) {
                adapter = CastAdapter {
                    findNavController().navigate(
                        DetailsMovieFragmentDirections.actionDetailsFragmentToCastFragment(
                            it.person.id,
                            it.person.name,
                            it.person.country?.name,
                            it.person.birthday
                        )
                    )
                }
            }
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            (adapter as? CastAdapter)?.submitList(list)
        }
    }


    private fun setColorStatus() {
        when (args.statusMovie) {
            "Ended" -> {
                binding.statusMovie.setTextColor(Color.parseColor("#fe0036"))
            }
            "Running" -> {
                binding.statusMovie.setTextColor(Color.parseColor("#12931F"))
            }
            "To Be Determined" -> {
                binding.statusMovie.setTextColor(Color.parseColor("#FFFFFFFF"))
            }
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setTitle(R.string.sorry)
            .setMessage(R.string.video_not_available)
            .setNegativeButton(R.string.OK) { _, _ ->
                requireContext().showToast(R.string.thanks)
            }
            .create()
            .show()
    }

}
