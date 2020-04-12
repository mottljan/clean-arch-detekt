package cz.ackee.cleanarch.detekt.features.articles.presentation.epoxy

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import cz.ackee.cleanarch.detekt.R
import cz.ackee.cleanarch.detekt.features.articles.domain.Article

@EpoxyModelClass(layout = R.layout.article_item)
abstract class ArticleModel : EpoxyModelWithHolder<ArticleModel.Holder>() {

    @EpoxyAttribute
    lateinit var article: Article

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onArticleClick: (Article) -> Unit

    override fun bind(holder: Holder) {
        with(holder) {
            rootView.setOnClickListener { onArticleClick(article) }
            txtTitle.text = article.title
            txtSummary.text = article.summary
            txtAuthor.text = article.author
        }
    }

    class Holder : EpoxyHolder() {

        lateinit var rootView: View
        lateinit var txtTitle: TextView
        lateinit var txtSummary: TextView
        lateinit var txtAuthor: TextView

        override fun bindView(itemView: View) {
            rootView = itemView
            txtTitle = itemView.findViewById(R.id.txtTitle)
            txtSummary = itemView.findViewById(R.id.txtSummary)
            txtAuthor = itemView.findViewById(R.id.txtAuthor)
        }
    }
}