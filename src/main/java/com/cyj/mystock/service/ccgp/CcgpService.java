package com.cyj.mystock.service.ccgp;

import com.cyj.mystock.cache.CcgpCache;
import com.cyj.mystock.dao.ccgp.CcgpDao;
import com.cyj.mystock.entity.Ccgp;
import com.cyj.mystock.entity.CcgpVO;
import com.cyj.mystock.thread.QueryStockThread;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CcgpService {

    @Autowired
    private CcgpDao<?> dao;
    @Autowired
    private RedisTemplate redisTemplate;

    public List<Ccgp> query(Map<String, String> map) {
        return dao.query(map);
    }

    public BigInteger queryCount(Map<String, String> map) {
        return dao.queryCount(map);
    }

    private final String REDIS_KEY = "REALTIME:";

    public List<Ccgp> query() {
        return dao.query();
    }

    /**
     * @param list
     * @return
     */
    public boolean add(final List<CcgpVO> list) {
        boolean result = false;
        if (list != null && list.size() > 0) {
            try {
                result = (boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
                    public Boolean doInRedis(RedisConnection connection)
                            throws DataAccessException {
                        RedisSerializer<String> serializer = getRedisSerializer();
                        for (CcgpVO ccgp : list) {
                            byte[] key = serializer.serialize(REDIS_KEY + ccgp.getCode());
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("rq", ccgp.getRq());
                            jsonObject.put("code", ccgp.getCode());
                            jsonObject.put("name", ccgp.getName());
                            jsonObject.put("buyprice", ccgp.getBuyprice());
                            jsonObject.put("sl", ccgp.getSl());
                            jsonObject.put("ccday", ccgp.getCcday());
                            jsonObject.put("nowprice", "");
                            jsonObject.put("yke", "");
                            jsonObject.put("zdl", "");
                            byte[] value = serializer.serialize(jsonObject.toString());
                            connection.setNX(key, value);
//							System.out.println("Redis key = "+REDIS_KEY+ccgp.getCode());
                        }
                        return true;
                    }
                }, false, true);
            } catch (RedisConnectionFailureException e) {
//				System.out.println(e.toString());
                for (CcgpVO ccgp : list) {
//					CcgpVO vo = new CcgpVO();
//					vo.setBuyprice(ccgp.getBuyprice());
//					vo.setCcday(ccgp.getCcday());
//					vo.setCode(ccgp.getCode());
//					vo.setName(ccgp.getName());
//					vo.setRq(ccgp.getRq());
//					vo.setSl(ccgp.getSl());
                    CcgpCache.set(ccgp.getCode(), ccgp);
                }
                result = true;
            }
        }
        return result;
    }

    public boolean add(CcgpVO ccgp) {
        boolean result = false;
        try {
            result = (boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    RedisSerializer<String> serializer = getRedisSerializer();

                    byte[] key = serializer.serialize(REDIS_KEY + ccgp.getCode());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("rq", ccgp.getRq());
                    jsonObject.put("code", ccgp.getCode());
                    jsonObject.put("name", ccgp.getName());
                    jsonObject.put("buyprice", ccgp.getBuyprice());
                    jsonObject.put("sl", ccgp.getSl());
                    jsonObject.put("ccday", ccgp.getCcday());
                    jsonObject.put("nowprice", "");
                    jsonObject.put("yke", "");
                    jsonObject.put("zdl", "");
                    byte[] value = serializer.serialize(jsonObject.toString());
                    connection.setNX(key, value);
//							System.out.println("Redis key = "+REDIS_KEY+ccgp.getCode());

                    return true;
                }
            }, false, true);
        } catch (RedisConnectionFailureException e) {
//				System.out.println(e.toString());
            CcgpCache.set(ccgp.getCode(), ccgp);
            result = true;
        }
        return result;
    }

    /**
     * 删除
     * <br>------------------------------<br>
     *
     * @param key
     */
    public void delete(String key) {
        List<String> list = new ArrayList<String>();
        list.add(REDIS_KEY + key);
        delete(list);
    }

    /**
     * 删除多个
     * <br>------------------------------<br>
     *
     * @param keys
     */
    public void delete(List<String> keys) {
        try {
            redisTemplate.delete(keys);
        } catch (RedisConnectionFailureException e) {
//			System.out.println(e.toString());
            for (String key : keys) {
                CcgpCache.remove(key);
            }
        }
    }

    public  void  deleteAll(){
        try {
            Set<String> keySet = redisTemplate.keys(REDIS_KEY + "*");
            for (String keyId : keySet) {
                delete(keyId.replaceAll(REDIS_KEY,""));
            }
        } catch (RedisConnectionFailureException e) {
//			System.out.println(e.toString());
            CcgpCache.clear();
        }
    }
    /**
     * 修改
     * <br>------------------------------<br>
     *
     * @param ccgp
     * @return
     */

    public boolean update(final CcgpVO ccgp) {
        String key = ccgp.getCode();
        if (get(key) == null) {
            throw new NullPointerException("数据行不存在, key = " + key);
        }
        boolean result = false;
        try {
            result = (boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    RedisSerializer<String> serializer = getRedisSerializer();
                    byte[] key = serializer.serialize(REDIS_KEY + ccgp.getCode());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("rq", ccgp.getRq());
                    jsonObject.put("code", ccgp.getCode());
                    jsonObject.put("name", ccgp.getName());
                    jsonObject.put("buyprice", ccgp.getBuyprice());
                    jsonObject.put("sl", ccgp.getSl());
                    jsonObject.put("ccday", ccgp.getCcday());
                    jsonObject.put("nowprice", ccgp.getNowprice());
                    jsonObject.put("yke", ccgp.getYke());
                    jsonObject.put("zdl", ccgp.getZdl());
                    byte[] value = serializer.serialize(jsonObject.toString());
                    connection.set(key, value);
//                    System.out.println("update Redis sussess ");
                    return true;
                }
            });
        } catch (RedisConnectionFailureException e) {
//			System.out.println(e.toString());
//            System.out.println("update cache sussess ");
            CcgpCache.set(key, ccgp);
            result = true;
        }
        return result;
    }

    /**
     * 通过key获取
     * <br>------------------------------<br>
     *
     * @param keyId
     * @return
     */
    public CcgpVO get(final String keyId) {
        CcgpVO result = null;
        try {
            result = (CcgpVO) redisTemplate.execute(new RedisCallback<CcgpVO>() {
                public CcgpVO doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    RedisSerializer<String> serializer = getRedisSerializer();
                    byte[] key = serializer.serialize(REDIS_KEY + keyId);
                    byte[] value = connection.get(key);
                    if (value == null) {
                        return null;
                    }
                    String jsonString = serializer.deserialize(value);
                    JSONObject jsonObject = JSONObject.fromObject(jsonString);
                    CcgpVO ccgp = new CcgpVO();
                    ccgp.setRq(jsonObject.getString("rq"));
                    ccgp.setCode(jsonObject.getString("code"));
                    ccgp.setName(jsonObject.getString("name"));
                    ccgp.setBuyprice(jsonObject.getString("buyprice"));
                    ccgp.setSl(jsonObject.getString("sl"));
                    ccgp.setCcday(jsonObject.getString("ccday"));
                    ccgp.setYke(jsonObject.getString("yke"));
                    ccgp.setZdl(jsonObject.getString("zdl"));
                    ccgp.setNowprice(jsonObject.getString("nowprice"));
                    return ccgp;
                }
            });
        } catch (RedisConnectionFailureException e) {
            System.out.println(e.toString());
            result = CcgpCache.get(keyId);
        }
        return result;
    }

    public List<CcgpVO> getAll() {
        List<CcgpVO> list = new ArrayList<CcgpVO>();
        try {
            Set<String> keySet = redisTemplate.keys(REDIS_KEY + "*");
            for (String keyId : keySet) {
                CcgpVO ccgpVO = get(keyId.replaceAll(REDIS_KEY, ""));
                list.add(ccgpVO);
            }
            if (keySet == null || keySet.size() == 0) {
                throw new RedisConnectionFailureException("redis is null");
            }
//			System.out.println("redis add  !");
        } catch (RedisConnectionFailureException e) {
            System.out.println(e.toString());
            List<Ccgp> ccgpList = dao.query();
            for (Ccgp ccgp : ccgpList) {
                CcgpVO vo = new CcgpVO();
                vo.setBuyprice(ccgp.getBuyprice());
                vo.setCcday(ccgp.getCcday());
                vo.setCode(ccgp.getCode());
                vo.setName(ccgp.getName());
                vo.setRq(ccgp.getRq());
                vo.setSl(ccgp.getSl());
                CcgpCache.set(ccgp.getCode(), vo);
                list.add(vo);
            }
            System.out.println(" CcgpCache add !");
        }
        return list;
    }

    public void init() {
        List<CcgpVO> list = new ArrayList<CcgpVO>();
        try {
            deleteAll();
            List<Ccgp> ccgpList = dao.query();
            for (Ccgp ccgp : ccgpList) {
                CcgpVO vo = new CcgpVO();
                vo.setBuyprice(ccgp.getBuyprice());
                vo.setCcday(ccgp.getCcday());
                vo.setCode(ccgp.getCode());
                vo.setName(ccgp.getName());
                vo.setRq(ccgp.getRq());
                vo.setSl(ccgp.getSl());
                list.add(vo);
            }
            this.add(list);
        } catch (RedisConnectionFailureException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取 RedisSerializer
     * <br>------------------------------<br>
     */
    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }

    public void reload(){
        QueryStockThread thread = QueryStockThread.getInstance();
        QueryStockThread.IsBreak=false;
        this.init();
        SimpleDateFormat format = new SimpleDateFormat("HHmm");
        Date date = new Date();
        String nowDateValue = format.format(date);
        int now = Integer.parseInt(nowDateValue);
        System.out.println("---------now---------"+now);
//        if(now<1510 && now>=915) {
            QueryStockThread.IsBreak=true;
            new Thread(thread).start();
//        }
    }
}
