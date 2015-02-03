package org.sogrey.mytools.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;
import org.sogrey.mytools.util.JavaBean.NameValue;

import android.util.Log;

public class Param {
	private String TAG=this.getClass().getSimpleName();
	List<NameValue> nameValueList;
	SoapObject table_data;
	
	
	public Param(){
		nameValueList = new ArrayList<NameValue>();
	}
	/**
	 * ��������
	 * @param name
	 * @param value
	 */
	public void setPair(String name, Object value) {
		NameValue nv= new NameValue();
		nv.setName(name);
		nv.setValue(value);
		nameValueList.add(nv);
	}
	
	/**
	 * ��ȡ�Ѿ�ͨ��setPair�������õĲ�������
	 * @return
	 */
	public List<NameValue> GetParamList(){
		return nameValueList;
	}
	
	/**
	 * ����obj������ȡcolume_list���ֶε�ֵ����������򽫷��ص�ֵ������HashMap�У���������ֵΪ���ַ���������һ��list����
	 * @param obj	���������ص�SoapObject
	 * @param colume_list	��Ҫ���ֶ��б�
	 * @return	List<Map<String, Object>>
	 */
	public List<Map<String, Object>> GetValueList(SoapObject obj, ArrayList<String> colume_list){
		SoapObject sobj = (SoapObject)obj.getProperty(0);
		if(((SoapObject)sobj.getProperty(1)).getPropertyCount()<1){
			return null;
		}
		table_data = (SoapObject)((SoapObject)sobj.getProperty(1)).getProperty(0);
		int count = table_data.getPropertyCount();
		Log.e(TAG, "table_data count:" + count);
		List<Map<String, Object>> map_list = new ArrayList<Map<String, Object>>();
		if(count > 0){
			for(int i=0; i<count; i++){
				SoapObject list = (SoapObject) table_data.getProperty(i);
				HashMap<String, Object> map = new HashMap<String, Object>();
				for (int j = 0; j < colume_list.size(); j++) {
					String name = colume_list.get(j).toString();
					if(list.hasProperty(name)){
						String value = list.getProperty(colume_list.get(j).toString()).toString().replace("'", "''").replace("anyType{}", "");
						map.put(name, value);
					}else{
						map.put(name, "");
					}
				}
				map_list.add(map);
			}
		}
		return map_list;
	}
	

	/**
	 * ����obj����(�Զ���JSON��ʽ)����ȡcolume_list���ֶε�ֵ����������򽫷��ص�ֵ������HashMap�У���������ֵΪ���ַ���������һ��list����
	 * @param obj	���������ص�SoapObject
	 * @param colume_list	��Ҫ���ֶ��б�
	 * @return	List<Map<String, Object>>
	 * @throws JSONException 
	 */
	public List<Map<String, Object>> GetValueList_JSON(SoapObject obj, ArrayList<String> colume_list) throws JSONException{
		table_data = obj;
		int count = table_data.getPropertyCount();
		Log.e(TAG, "table_data count:" + count);
		List<Map<String, Object>> map_list=null;
		if(count > 0){
			map_list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < count; i++) {
				String str_result = obj.getProperty(i).toString();
				str_result = str_result.substring(0, str_result.length()-1);
				JSONObject jsonObject = new JSONObject(str_result.substring(1, str_result.length()));
				HashMap<String, Object> map = new HashMap<String, Object>();
				for (int j = 0; j < colume_list.size(); j++) {
					String name = colume_list.get(j);
					String value = jsonObject.getString(name);
					map.put(name, value);
				}
				map_list.add(map);
			}
		}
		return map_list;
	}
	  /**
     * �˷���ר����Ի�ȡ�������籡δ����Ŀ
     * @param obj	���������ص�SoapObject
     * @param colume_list	��Ҫ���ֶ��б�
     * @return	List<Map<String, Object>>
     * @throws JSONException
     */
    public List<Map<String, Object>> GetValueListJSON(SoapObject obj, ArrayList<String> colume_list) throws JSONException{
        if(obj.getPropertyCount()<=0){
            return null;
        }
        if(obj.getProperty(0)==null){
            return null;
        }
        if(obj.getProperty(0).toString().equals("")){
            return null;
        }
        JSONArray jsonArr = new JSONArray(obj.getProperty(0).toString());
        int count = jsonArr.length();
        Log.e(TAG, "table_data count:" + count);
        List<Map<String, Object>> map_list=null;
        if(count > 0){
            map_list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < count; i++) {
                String str_result = jsonArr.get(i).toString();
                JSONObject jsonObject = new JSONObject(str_result);
                HashMap<String, Object> map = new HashMap<String, Object>();
                for (int j = 0; j < colume_list.size(); j++) {
                    String name = colume_list.get(j);
                    String value="";
                    if(jsonObject.has(name)){
                        value=jsonObject.getString(name);
                    }
                    map.put(name, value);
                }
                map_list.add(map);
            }
        }
        return map_list;
    }
}
