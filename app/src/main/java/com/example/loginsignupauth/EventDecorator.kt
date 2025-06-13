import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.*
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*

class EventDecorator(private val eventDays: HashSet<CalendarDay>) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return eventDays.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(ForegroundColorSpan(Color.GREEN)) // Highlight event days in RED
    }
}
