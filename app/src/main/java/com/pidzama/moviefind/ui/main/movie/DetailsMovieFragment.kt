package com.pidzama.moviefind.ui.main.movie

import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pidzama.moviefind.R
import com.pidzama.moviefind.data.model.cast.CastItem
import com.pidzama.moviefind.data.model.movies.Movie
import com.pidzama.moviefind.data.model.seasons.SeasonsItem
import com.pidzama.moviefind.databinding.FragmentDetailsBinding
import com.pidzama.moviefind.repository.SharedPreferenceRepository
import com.pidzama.moviefind.ui.main.cast.adapter.CastAdapter
import com.pidzama.moviefind.ui.main.seasons.adapter.SeasonsAdapter
import com.pidzama.moviefind.utils.StatusMovie
import com.pidzama.moviefind.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsMovieFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()
    lateinit var likeMovie: Movie
    private var isFavouriteHeroIcon = false

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
            val saveShared = SharedPreferenceRepository(requireContext())
            if (!likeMovie.isFavorite) {
                binding.buttonAddFavorite.setImageResource(R.drawable.add_favorite)
                saveShared.setFavourite(likeMovie.id.toString(), true)
                viewModel.chooseMovieFavorite(likeMovie)
                requireContext().showToast(R.string.movie_add_favorite)
            } else {
                binding.buttonAddFavorite.setImageResource(R.drawable.ic_favoritepage)
                saveShared.setFavourite(likeMovie.id.toString(), false)
                requireContext().showToast(R.string.movie_delete_favorite)
                viewModel.chooseMovieFavorite(likeMovie)
            }
            ObjectAnimator.ofFloat(binding.buttonAddFavorite, View.ALPHA, 0.3F, 1F).start()
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
            val saveShared = SharedPreferenceRepository(requireContext())
            currentMovie.observe(viewLifecycleOwner) {
                likeMovie = it
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
                val booleanFavourite = saveShared.getFavourite(likeMovie.id.toString())
                isFavouriteHeroIcon = if (isFavouriteHeroIcon != booleanFavourite) {
                    binding.buttonAddFavorite.setImageResource(R.drawable.add_favorite)
                    true
                } else {
                    binding.buttonAddFavorite.setImageResource(R.drawable.ic_favoritepage)
                    false
                }
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
            StatusMovie.ENDED.status -> {
                binding.statusMovie.setTextColor(Color.parseColor("#fe0036"))
            }
            StatusMovie.RUNNING.status -> {
                binding.statusMovie.setTextColor(Color.parseColor("#12931F"))
            }
            StatusMovie.TO_BE_DETERMINED.status -> {
                binding.statusMovie.setTextColor(Color.parseColor("#FFFFFFFF"))
            }
        }
    }

    private fun showAlertDialog() {
        val dialog = Dialog(requireContext())
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.info_alert_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnOK: Button = dialog.findViewById(R.id.button_ok)

        btnOK.setOnClickListener {
            dialog.dismiss()
            requireContext().showToast(R.string.thanks)
        }
        dialog.show()
    }
}
