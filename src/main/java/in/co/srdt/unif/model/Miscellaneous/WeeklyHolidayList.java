package in.co.srdt.unif.model.Miscellaneous;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import in.co.srdt.unif.model.newperson.NationalDetails;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

public class WeeklyHolidayList {

	
	
	private List<WeeklyHoliday>weeklyholiday ;
	
	public WeeklyHolidayList() {
		weeklyholiday = new ArrayList<>();
		weeklyholiday.add(new WeeklyHoliday());
	}
	
	


	public List<WeeklyHoliday> getWeeklyholiday() {
		return weeklyholiday;
	}

	public void setWeeklyholiday(List<WeeklyHoliday> weeklyholiday) {
		this.weeklyholiday = weeklyholiday;
	}

	@Override
	public String toString() {
		return "WeeklyHolidayList [weeklyholiday=" + weeklyholiday + "]";
	}

	
	
	
	
}
