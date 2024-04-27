import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androiddev_project.DbHelper
import com.example.androiddev_project.Film
import com.example.androiddev_project.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addFilmsToDb()
    }

    private fun addFilmsToDb() {
        val dbHelper = DbHelper(requireContext(), null)
        val films = listOf(
            Film("Film 1", "Description of Film 1", 4.5, "Action"),
            Film("Film 2", "Description of Film 2", 3.8, "Horror"),
            Film("Film 3", "Description of Film 3", 4.0, "Fantasy"),
            Film("Film 4", "Description of Film 4", 4.2, "Action"),
            Film("Film 5", "Description of Film 5", 3.9, "Horror"),
            Film("Film 6", "Description of Film 6", 4.1, "Fantasy"),
            Film("Film 7", "Description of Film 7", 4.3, "Action"),
            Film("Film 8", "Description of Film 8", 3.7, "Horror"),
            Film("Film 9", "Description of Film 9", 4.4, "Fantasy"),
            Film("Film 10", "Description of Film 10", 4.6, "Action")
        )

        for (film in films) {
            dbHelper.addFilm(film)
        }
    }
}
