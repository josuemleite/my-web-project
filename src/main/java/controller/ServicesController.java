package controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelException;
import model.Service;
import model.User;
import model.dao.DAOFactory;
import model.dao.ServiceDAO;

@WebServlet(urlPatterns = {"/services", "/service/form", "/service/insert", "/service/update", "/service/delete"})
public class ServicesController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		
		switch (action) {
		case "/crud-manager/service/form": {
			CommonsController.listUsers(req);
			req.setAttribute("action", "insert");
			ControllerUtil.forward(req, resp, "/form-service.jsp");
			break;
		}
		case "/crud-manager/service/update": {
			CommonsController.listUsers(req);
			Service s = loadService(req);
			req.setAttribute("service", s);
			req.setAttribute("action", "update");
			ControllerUtil.forward(req, resp, "/form-service.jsp");
			break;
		}
		default:
			listServices(req);
			ControllerUtil.transferSessionMessagesToRequest(req);
			ControllerUtil.forward(req, resp, "/services.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		
		if (action == null || action.equals("")) {
			ControllerUtil.forward(req, resp, "/index.jsp");
			return;
		}
		
		switch (action) {
		case "/crud-manager/service/insert": {
			insertService(req, resp);
			break;
		}
		case "/crud-manager/service/update": {
			updateService(req, resp);
			break;
		}
		case "/crud-manager/service/delete": {
			deleteService(req, resp);
			break;
		}
		default:
			System.out.println("URL inválida " + action);
			break;
		}
		
		ControllerUtil.redirect(resp, req.getContextPath() + "/services");
	}
	
	private Service loadService(HttpServletRequest req) {
		String serviceIdParameter = req.getParameter("serviceId");
		
		int serviceId = Integer.parseInt(serviceIdParameter);
		
		ServiceDAO dao = DAOFactory.createDAO(ServiceDAO.class);
		
		try {
			Service service = dao.findById(serviceId);
			
			if (service == null)
				throw new ModelException("Serviço não encontrado para alteração");
			
			return service;
		} catch (ModelException e) {
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
		
		return null;
	}
	
	private void listServices(HttpServletRequest req) {
		ServiceDAO dao = DAOFactory.createDAO(ServiceDAO.class);
		
		List<Service> services = null;
		try {
			services = dao.listAll();
		} catch (ModelException e) {
			e.printStackTrace();
		}
		
		if (services != null)
			req.setAttribute("services", services);
	}
	
	private void insertService(HttpServletRequest req, HttpServletResponse resp) {
		String typeOfService = req.getParameter("type_of_service");
		Double valuePerHour = Double.parseDouble(req.getParameter("value_per_hour"));
		String expertiseLevel = req.getParameter("expertise_level");
		String portfolio = req.getParameter("portfolio");
		Integer userId = Integer.parseInt(req.getParameter("user"));
		
		try {
			Service service = new Service();
			service.setTypeOfService(typeOfService);
			service.setValuePerHour(valuePerHour);
			service.setExpertiseLevel(expertiseLevel);
			service.setPortfolio(portfolio);
			service.setUser(new User(userId));
			
			ServiceDAO dao = DAOFactory.createDAO(ServiceDAO.class);
			
			if (dao.save(service)) {
				ControllerUtil.sucessMessage(req, "Serviço '" + service.getTypeOfService() + "' salvo com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Serviço '" + service.getTypeOfService() + "' não pode ser salvo.");
			}
		} catch (ModelException e) {
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
	
	private void updateService(HttpServletRequest req, HttpServletResponse resp) {
		String typeOfService = req.getParameter("type_of_service");
		Double valuePerHour = Double.parseDouble(req.getParameter("value_per_hour"));
		String expertiseLevel = req.getParameter("expertise_level");
		String portfolio = req.getParameter("portfolio");
		Integer userId = Integer.parseInt(req.getParameter("user"));
		
		try {
			Service service = loadService(req);
			service.setTypeOfService(typeOfService);
			service.setValuePerHour(valuePerHour);
			service.setExpertiseLevel(expertiseLevel);
			service.setPortfolio(portfolio);
			service.setUser(new User(userId));
			
			ServiceDAO dao = DAOFactory.createDAO(ServiceDAO.class);
			
			if (dao.update(service)) {
				ControllerUtil.sucessMessage(req, "Serviço '" + service.getTypeOfService() + "' atualizado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Serviço '" + service.getTypeOfService() + "' não pode ser atualizado.");
			}				
		} catch (ModelException e) {
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
	
	private void deleteService(HttpServletRequest req, HttpServletResponse resp) {
		String serviceIdParameter = req.getParameter("id");
		
		int serviceId = Integer.parseInt(serviceIdParameter);
		
		ServiceDAO dao = DAOFactory.createDAO(ServiceDAO.class);
		
		try {
			Service service = dao.findById(serviceId);
			
			if (service == null)
				throw new ModelException("Serviço não encontrado para remoção.");
			
			if (dao.delete(service)) {
				ControllerUtil.sucessMessage(req, "Serviço '" + service.getTypeOfService() + "' deletado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Serviço '" + service.getTypeOfService() + "' não pode ser deletado. Há dados relacionados ao usuário.");
			}
		} catch (ModelException e) {
			if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				ControllerUtil.errorMessage(req, e.getMessage());
			}
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
}
