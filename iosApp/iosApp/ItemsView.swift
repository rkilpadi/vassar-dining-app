//
//  ItemsView.swift
//  iosApp
//
//  Created by Lawrence Wang on 6/11/23.
//

import SwiftUI
import shared

struct ItemsView: View {
    var items: [MenuItem]
    @StateObject var user: User
    @Binding var showRes: Bool
    @Binding var showFav: Bool
    
    var body: some View {
        ForEach(items, id: \.self) { item in
            if (!(showFav && !self.isFav(item: item)) && !(showRes && !self.hasRes(item: item))) {
                ZStack {
                    RoundedRectangle(cornerRadius: 5)
                        .fill(Color("teal_200").opacity(0.7))
                    VStack(alignment: .leading) {
                        HStack {
                            Button {
                                if (user.favItems.isEmpty) {
                                    user.favItems.append(item)
                                } else {
                                    for i in user.favItems.indices {
                                        if (user.favItems[i] == item) {
                                            user.favItems.remove(at: i)
                                            break
                                        } else if (i == (user.favItems.count - 1)) {
                                            user.favItems.append(item)
                                        }
                                    }
                                }
                                
                                user.saveToStorage()
                            } label: {
                                if (user.favItems.contains { $0 == item }) {
                                    Image("filledHeart")
                                        .resizable()
                                        .frame(width: 30, height: 30)
                                } else {
                                    Image("unfilledHeart")
                                        .resizable()
                                        .frame(width: 30, height: 30)
                                }
                            }
                            Text((item.label ?? "").capitalized)
                                .font(.headline)
                            Spacer()
                            RestrictionsImage(restrictionsList: item.cor_icon ?? [:])
                        }
                    }
                    .padding()
                    .foregroundColor(.black)
                }
            }
        }
    }
    
    func hasRes(item: MenuItem) -> Bool {
        let mapRes: [String: String] = item.cor_icon ?? [:]
        
        for (res, active) in user.dietRes {
            if (active && mapRes.keys.contains { $0 == res.restrictionId }) {
                return true
            }
        }
        
        return false
    }
    
    func isFav(item: MenuItem) -> Bool {
        for favItem in user.favItems {
            if (favItem == item) {
                return true
            }
        }
        
        return false
    }
}
