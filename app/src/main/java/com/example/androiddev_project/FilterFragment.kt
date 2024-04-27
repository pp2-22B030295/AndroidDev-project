import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddev_project.DbHelper
import com.example.androiddev_project.Film
import com.example.androiddev_project.adapter.FilmAdapter
import com.example.androiddev_project.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FilmAdapter
    private lateinit var films: List<Film>
    private lateinit var dbHelper: DbHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = DbHelper(requireContext(), null)

        films = db.getFilms()
        adapter = FilmAdapter(films)
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())

        binding.fantasyButton.setOnClickListener { filterFilmsByCategory("fantasy") }
        binding.horrorButton.setOnClickListener { filterFilmsByCategory("horror") }
        binding.actionButton.setOnClickListener { filterFilmsByCategory("action") }
        binding.showAllButton.setOnClickListener { showAllFilms() }
    }

    private fun filterFilmsByCategory(category: String) {
        val filteredFilms = films.filter { it.category == category }
        updateRecyclerView(filteredFilms)
    }

    private fun showAllFilms() {
        films = dbHelper.getFilms()
        updateRecyclerView(films)
    }

    private fun updateRecyclerView(films: List<Film>) {
        adapter.filmList = films
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
