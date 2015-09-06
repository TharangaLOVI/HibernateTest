package com.lovi.test.webapp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lovi.test.models.Person;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping()
	public @ResponseBody  String index(HttpServletRequest request){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		List<Person> persons = (List<Person>)session.createQuery("FROM Person").list();
			
		tx.commit();
		session.close();
	
		return prepareJSONArrayResult(persons);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody  String insert(@ModelAttribute Person person){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		int id = (Integer)session.save(person);
		
		tx.commit();
		session.close();
		
		
		return prepareJSONresult(1, "OK - " + id);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody  String update(@ModelAttribute Person person){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		session.update(person);
		
		tx.commit();
		session.close();

		return prepareJSONresult(1, "OK");
		
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public @ResponseBody  String get(@PathVariable("id") String personId){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Person person = (Person)session.get(Person.class, Integer.parseInt(personId));
		
		tx.commit();
		session.close();
		
		
		return prepareJSONresult(1, "id : " + person.getEmpId() + " ,name : " + person.getEmpName());
		
	}
	
	private String prepareJSONresult(int status, String value) {

		JSONObject jsonResponce = new JSONObject();
		jsonResponce.put("status", status);
		jsonResponce.put("value", value);
		return jsonResponce.toString();

	}
	
	private String prepareJSONArrayResult(Object objectValue) {

		JSONObject jsonResponce = new JSONObject();
		try {
			if (objectValue != null) {
				jsonResponce.put("status", 1);
				ObjectMapper objectMapper = new ObjectMapper();
				String stringObject = objectMapper
						.writeValueAsString(objectValue);
				JSONArray jsonArrayValue = new JSONArray(stringObject);
				jsonResponce.put("value", jsonArrayValue);

			} else {
				throw new Exception("Error");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			jsonResponce.put("status", -1);
			jsonResponce.put("value", e.getMessage().toString());
		}
		return jsonResponce.toString();

	}
	
	private String prepareJSONresult(JSONObject objectValue) {

		JSONObject jsonResponce = new JSONObject();
		try {
			if (objectValue != null) {

				jsonResponce.put("value", objectValue);
				jsonResponce.put("status", 1);

			} else {
				throw new Exception("Error");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			jsonResponce.put("status", -1);
			jsonResponce.put("value", e.getMessage().toString());
		}
		return jsonResponce.toString();

	}
	
	
}
