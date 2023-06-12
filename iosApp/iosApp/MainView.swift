//
//  ContentView.swift
//  iosApp
//
//  Created by Lawrence Wang on 6/7/23.
//

import SwiftUI
import shared

struct MainView: View {
    
    @State var presentSideMenu = false
    @State var selectedSideMenuTab = 0
    @StateObject var user = User()
    @StateObject var viewModel: MenuViewModel
    
    var body: some View {
        ZStack{
            
            if (viewModel.searching) {
                LoadingView()
            } else {
                TabView(selection: $selectedSideMenuTab) {
                    HomeView(presentSideMenu: $presentSideMenu, viewModel: viewModel, user: user)
                        .tag(0)
                    FavoritesView(presentSideMenu: $presentSideMenu, viewModel: viewModel, user: user)
                        .tag(1)
                    RestrictionsView(presentSideMenu: $presentSideMenu, user: user, viewModel: viewModel)
                        .tag(2)
                }
                
                SideMenu(isShowing: $presentSideMenu, content: AnyView(SideMenuView(selectedSideMenuTab: $selectedSideMenuTab, presentSideMenu: $presentSideMenu)))
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        let viewModel = MenuViewModel()
        
        viewModel.searching = false
        
        viewModel.dayParts = [
            DayPart(label: "Breakfast", stations: [Station(label: "Station 1", items: [""]), Station(label: "Station 11", items: [""])]),
            DayPart(label: "Lunch", stations: [Station(label: "Station 2", items: [""]), Station(label: "Station 22", items: [""])]),
            DayPart(label: "Light Lunch", stations: [Station(label: "Station 3", items: [""]), Station(label: "Station 33", items: [""])]),
            DayPart(label: "Dinner", stations: [Station(label: "Station 4", items: [""]), Station(label: "Station 44", items: [""])])]
        
        
        return MainView(viewModel: viewModel)
    }
}

