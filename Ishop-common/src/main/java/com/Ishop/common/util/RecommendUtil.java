package com.Ishop.common.util;



import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class RecommendUtil {
//    @Resource
//    UserMapper userMapper;
//    @Resource
//    BlogMapper blogMapper;
//    public List<Blog> recommend(Integer userId) throws TasteException {
//        List<UserOperation> userList = userMapper.getAllUserPreference();
//        System.out.println(userList);
//        //创建数据模型
//        DataModel dataModel = this.createDataModel(userList);
//        //获取用户相似程度,使用皮尔逊相关系数
//        UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
//        //获取用户邻居,固定距离
//        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(5, similarity, dataModel);
//        //构建推荐器
//        Recommender recommender = new GenericUserBasedRecommender(dataModel,userNeighborhood,similarity);
//        //默认推荐10个
//        List<RecommendedItem> recommendedItems = recommender.recommend(userId, 10);
//        List<Long> itemIds = recommendedItems.stream().map(RecommendedItem::getItemID).collect(Collectors.toList());
//        List<Blog> blogList = new ArrayList<>();
//        for(long bid : itemIds){
//            blogList.add(blogMapper.selectById(bid));
//        }
//        return blogList;
//    }
//    private DataModel createDataModel(List<UserOperation> userArticleOperations) {
//        FastByIDMap<PreferenceArray> fastByIdMap = new FastByIDMap<>();
//        Map<Integer, List<UserOperation>> map = userArticleOperations.stream().collect(Collectors.groupingBy(UserOperation::getId));
//        Collection<List<UserOperation>> list = map.values();
//        for(List<UserOperation> userPreferences : list){
//            GenericPreference[] array = new GenericPreference[userPreferences.size()];
//            for(int i = 0; i < userPreferences.size(); i++){
//                UserOperation userPreference = userPreferences.get(i);
//                GenericPreference item = new GenericPreference(userPreference.getId(), userPreference.getBid(), userPreference.getValue());
//                array[i] = item;
//            }
//            fastByIdMap.put(array[0].getUserID(), new GenericUserPreferenceArray(Arrays.asList(array)));
//        }
//        return new GenericDataModel(fastByIdMap);
//    }

}
