//
//  User.swift
//  iosApp
//
//  Created by Lawrence Wang on 6/11/23.
//

import SwiftUI
import shared

class User: ObservableObject, Codable {
    enum CodingKeys: CodingKey {
        case id, active, school, itemLabel, itemDes, itemResId, itemTier
    }
    
    @Published var dietRes: [(res: DietaryRestriction, active: Bool)] = []
    @Published var favItems: [MenuItem] = []
    @Published var school: String = ""
    @State var allRes: [DietaryRestriction] = DietaryRestriction.companion.createArray()
    
    init() {
        self.loadFromStorage()
        
        if (dietRes.isEmpty) {
            for res in allRes {
                dietRes.append((res: res, active: false))
            }
        }
    }
    
    required init(from decoder: Decoder) throws {
        var ids: Array<String> = []
        var active: Array<Bool> = []
        var itemLabel: Array<String> = []
        var itemDes: Array<String> = []
        var itemResId: Array<Array<String>> = []
        var itemTier: Array<Int> = []
        
        let container = try decoder.container(keyedBy: CodingKeys.self)
        
        school = try container.decode(String.self, forKey: .school)
        
        ids = try container.decode(Array<String>.self, forKey: .id)
        active = try container.decode(Array<Bool>.self, forKey: .active)
        
        for i in ids.indices {
            dietRes.append((DietaryRestriction.companion.getById(id: ids[i]), active[i]))
        }
        
        itemLabel = try container.decode(Array<String>.self, forKey: .itemLabel)
        itemDes = try container.decode(Array<String>.self, forKey: .itemDes)
        itemResId = try container.decode(Array<Array<String>>.self, forKey: .itemResId)
        itemTier = try container.decode(Array<Int>.self, forKey: .itemTier)
        
        for i in itemLabel.indices {
            favItems.append(MenuItem(label: itemLabel[i], description: itemDes[i], cor_icon: translatetoRes(ids: itemResId[i]), tier: Int32(itemTier[i])))
        }
    }
    
    func encode(to encoder: Encoder) throws {
        var ids: Array<String> = []
        var active: Array<Bool> = []
        var itemLabel: Array<String> = []
        var itemDes: Array<String> = []
        var itemResId: Array<Array<String>> = []
        var itemTier: Array<Int> = []
        
        var container = encoder.container(keyedBy: CodingKeys.self)
        
        try container.encode(school, forKey: .school)
        
        for i in dietRes.indices {
            ids.append(dietRes[i].res.restrictionId)
            active.append(dietRes[i].active)
        }
        
        try container.encode(ids, forKey: .id)
        try container.encode(active, forKey: .active)
        
        for i in favItems.indices {
            itemLabel.append(favItems[i].label ?? "")
            itemDes.append(favItems[i].description_ ?? "")
            itemResId.append(Array(favItems[i].cor_icon?.keys ?? [:].keys))
            itemTier.append(Int(favItems[i].tier))
        }
        
        try container.encode(itemLabel, forKey: .itemLabel)
        try container.encode(itemDes, forKey: .itemDes)
        try container.encode(itemResId, forKey: .itemResId)
        try container.encode(itemTier, forKey: .itemTier)
    }
    
    func translatetoRes(ids: Array<String>) -> [String: String] {
        var dict: [String: String] = [:]
        
        for id in ids {
            dict[id] = DietaryRestriction.companion.getById(id: id).label
        }
        
        return dict
    }
    
    func loadFromStorage() {
        if let savedUser = UserDefaults.standard.object(forKey: "SavedUser") as? Data {
            let decoder = JSONDecoder()
            let savedData = try? decoder.decode(User.self, from: savedUser)

            dietRes = savedData?.dietRes ?? []
            favItems = savedData?.favItems ?? []
            school = savedData?.school ?? ""
        }
    }
    
    func saveToStorage() {
        let encoder = JSONEncoder()
        if let encoded = try? encoder.encode(self) {
            UserDefaults.standard.set(encoded, forKey: "SavedUser")
        }
    }
}
