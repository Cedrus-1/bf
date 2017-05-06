package com.persistence;

import java.util.List;

import com.bean.Relation;

public interface RelationMapper {
	
	List<Relation> getRelationsByID(int id);
	
	List<Relation> getApplyRelationByID(int id);
	
	Relation getRelationByUserIDs(int applyID, int agreeID);
	
	int addRelation(Relation relation);
	
	int updateRelation(Relation relation);
	
	int deleteRelation(int relationID);

}
