package cz.ackee.cleanarch.detekt.features.articles.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cz.ackee.cleanarch.detekt.databinding.ArticlesFragmentBinding
import cz.ackee.cleanarch.detekt.features.articles.presentation.epoxy.ArticlesController
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticlesFragment : Fragment() {

    private val viewModel: ArticlesViewModel by viewModel()

    private var binding: ArticlesFragmentBinding? = null

    private val articlesController = ArticlesController { viewModel.deleteArticle(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ArticlesFragmentBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.setUpViews()
        observeData()
    }

    private fun ArticlesFragmentBinding.setUpViews() {
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setController(articlesController)

        fab.setOnClickListener { viewModel.insertRandomArticle() }
    }

    private fun observeData() {
        viewModel.articles.observe(viewLifecycleOwner, Observer {
            articlesController.setData(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}