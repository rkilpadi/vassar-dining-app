//
//  File.swift
//  iosApp
//
//  Created by Lawrence Wang on 6/7/23.
//

import SwiftUI
import shared

struct HomeView: View {
    
    @Binding var presentSideMenu: Bool
    @StateObject var viewModel: MenuViewModel
    @StateObject var user: User
    @State var mapItems: [(station: Station, items: [MenuItem])] = []
    @State var moreDetailsMap: [Station: Bool] = [:]
    @State var frames: [CGRect] = []
    @State var showRes: Bool = false
    @State var showFav: Bool = false
    @State var dayPartSelected: Int = 0
    
    var body: some View {
        VStack(spacing: 0) {
            HStack{
                Button{
                    presentSideMenu.toggle()
                } label: {
                    Image("Menu")
                        .resizable()
                        .frame(width: 32, height: 32)
                }
                .padding(.trailing)
                Text("Menu").bold()
                Spacer()
                Button {
                    showRes.toggle()
                } label: {
                    if showRes {
                        Image("dietaryRestrictionFilled")
                            .resizable()
                            .frame(width: 25, height: 25)
                    } else {
                        Image("dietaryRestriction")
                            .resizable()
                            .frame(width: 25, height: 25)
                    }
                }
                Button {
                    showFav.toggle()
                } label: {
                    if showFav {
                        Image("filledHeart")
                            .resizable()
                            .frame(width: 25, height: 25)
                    } else {
                        Image("unfilledHeart")
                            .resizable()
                            .frame(width: 25, height: 25)
                    }
                }
            }.padding().background(Color("purple_700"))
            
            HStack (alignment: .center, spacing: 20) {
                Spacer(minLength: 0)
                ForEach(viewModel.dayParts.indices, id: \.self) { i in
                    Button {
                        loadDayPart(i: i)
                        dayPartSelected = i
                    } label: {
                        Text(String(viewModel.dayParts[i].label))
                            .foregroundColor(Color.black)
                            .bold()
                            .lineLimit(1)
                            .font(.system(size: 15))
                            .background(LinearGradient(colors: (i == dayPartSelected) ? [Color.clear, .mint.opacity(0.3), .mint.opacity(0.35), .mint.opacity(0.7), Color("teal_200"), .mint.opacity(0.7), .mint.opacity(0.35), .mint.opacity(0.3), Color.clear] : [Color.clear], startPoint: .leading, endPoint: .trailing))
                    }.onAppear {
                        Task {
                            loadDayPart(i: 0)
                        }
                    }
                }
                Spacer(minLength: 0)
            }
            .padding()
            .background(LinearGradient(colors: [Color("purple_700"), .mint.opacity(0.3), .mint.opacity(0.35), .mint.opacity(0.7)], startPoint: .top, endPoint: .bottom))
            
            ScrollView {
                MenuView(mapItems: $mapItems, moreDetailsMap: $moreDetailsMap, frames: $frames, user: user, showRes: $showRes, showFav: $showFav)
            }
            .coordinateSpace(name: "container")
            .onPreferenceChange(FramePreference.self, perform: {
                frames = $0.sorted(by: { $0.minY < $1.minY
                })
            })
        }
    }
    
    func loadDayPart(i: Int) {
        var index: Int
        
        if i > (viewModel.dayParts.count - 1) {
            index = 0
        } else {
            index = i
        }
        
        let data = viewModel.dayParts[index]
        
        mapItems = []
        
        for station in data.stations {
            let items = station.stationItems as NSArray as! [MenuItem]
            
            mapItems.append((station: station, items: items))
        }
        
        for (station, _) in mapItems {
            moreDetailsMap[station] = true
        }
    }
}

struct MenuView: View {
    
    @Binding var mapItems: [(station: Station, items: [MenuItem])]
    @Binding var moreDetailsMap: [Station: Bool]
    @Binding var frames: [CGRect]
    @StateObject var user: User
    @Binding var showRes: Bool
    @Binding var showFav: Bool
    
    var body: some View {
        VStack(spacing: 0) {
            ForEach(mapItems, id: \.0) { (station, items) in
                VStack {
                    // Station
                    ZStack {
                        Rectangle()
                            .fill(Color("teal_200"))
                        VStack(alignment: .leading) {
                            HStack {
                                Button {
                                    moreDetailsMap[station]?.toggle()
                                } label: {
                                    Image("moreDetailsArrow")
                                        .resizable()
                                        .frame(width: 30, height: 30)
                                }
                                .rotationEffect(.degrees(moreDetailsMap[station] ?? false ? 90 : 0))
                                .padding(.trailing, 5)
                                Text(station.label.capitalized)
                                    .font(.headline)
                                Spacer()
                            }
                        }
                        .padding()
                        .foregroundColor(.black)
                    }
                    .sticky(frames)
                    
                    // Items
                    if (moreDetailsMap[station] ?? false) {
                        VStack {
                            ItemsView(items: items, user: user, showRes: $showRes, showFav: $showFav)
                        }
                        .padding([.leading, .trailing])
                    }
                    
                    Spacer()
                }
            }
        }
    }
}

struct RestrictionsImage: View {
    
    var restrictionsList: [String: String]
    
    var body: some View {
        
        ForEach(Array(restrictionsList.keys), id: \.self) { key in
            Image(DietaryRestriction.companion.getById(id: key).iconId)
                .resizable()
                .frame(width: 15, height: 15)
        }
    }
}
