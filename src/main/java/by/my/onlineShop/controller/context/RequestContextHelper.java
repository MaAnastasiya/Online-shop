package by.my.onlineShop.controller.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestContextHelper { //Помощник по контексту запроса
    private final HttpServletRequest request;

    public RequestContextHelper(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public RequestContext createContext() { // формирует контекст запроса. Разбираем наш запрос
        Enumeration<String> requestParameterNames = request.getParameterNames(); // возвращает переданные имена параметров
        Enumeration<String> requestAttributesNames = request.getAttributeNames(); // получает все имена атрибутов объекта запроса и возвращает одно
        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributesNames = session.getAttributeNames();// все объекты этого сеанса
        Map<String, String> requestParameters = createRequestParametersMap(requestParameterNames);
        Map<String, Object> requestAttributes = createRequestAttributesMap(requestAttributesNames);
        Map<String, Object> sessionAttributes = createSessionAttributesMap(sessionAttributesNames, session);
        return new RequestContext(requestParameters, requestAttributes, sessionAttributes);
    }

    public void updateRequest(RequestContext context) { //запрос на обновление
        Map<String, Object> requestAttributes = context.getAllRequestAttributes();
        Map<String, Object> sessionAttributes = context.getAllSessionAttributes();
        HttpSession session = request.getSession();
        fillSessionAttributes(session, sessionAttributes);
        fillRequestAttributes(requestAttributes);
    }

    private void fillRequestAttributes(Map<String, Object> attributes) { //атрибуты запроса на заполнение
        for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
            request.setAttribute(attribute.getKey(), attribute.getValue());
        }
    }

    private void fillSessionAttributes(HttpSession session, Map<String, Object> attributes) { // заполнение Атрибутов сеанса
        for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
            session.setAttribute(attribute.getKey(), attribute.getValue());
        }
    }

    private Map<String, String> createRequestParametersMap(Enumeration<String> names) { // передали все имена параметров
        Map<String, String> resultMap = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = request.getParameter(name);//значение этого параметра
            resultMap.put(name, value);
        }
        return resultMap;
    }

    private Map<String, Object> createRequestAttributesMap(Enumeration<String> names) {
        Map<String, Object> resultMap = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Object value = request.getAttribute(name);
            resultMap.put(name, value);
        }
        return resultMap;
    }

    private Map<String, Object> createSessionAttributesMap(Enumeration<String> names, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Object value = session.getAttribute(name);
            resultMap.put(name, value);
        }
        return resultMap;
    }
}

