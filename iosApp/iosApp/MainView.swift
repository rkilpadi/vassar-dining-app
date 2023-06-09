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
    @StateObject var viewModel: MenuViewModel
    
    var body: some View {
        ZStack{
            
            TabView(selection: $selectedSideMenuTab) {
                HomeView(presentSideMenu: $presentSideMenu, viewModel: viewModel)
                    .tag(0)
//                    FavoriteView(presentSideMenu: $presentSideMenu)
//                        .tag(1)
//                    RestrictionsView(presentSideMenu: $presentSideMenu)
//                        .tag(2)
            }
            
            SideMenu(isShowing: $presentSideMenu, content: AnyView(SideMenuView(selectedSideMenuTab: $selectedSideMenuTab, presentSideMenu: $presentSideMenu)))
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        let viewModel = MenuViewModel()
        
        viewModel.menus = [
            MealtimeMenu(cafe: "deece", date: "24-05-2023", label: "Breakfast", menuItems: [MealtimeItem(name: "chicken", id: "123", description: "", station: "", dietaryRestrictions: KotlinMutableSet<DietaryRestriction>())]),
            MealtimeMenu(cafe: "deece", date: "24-05-2023", label: "Lunch", menuItems: [MealtimeItem(name: "pork", id: "123", description: "", station: "", dietaryRestrictions: KotlinMutableSet<DietaryRestriction>())]),
            MealtimeMenu(cafe: "deece", date: "24-05-2023", label: "Light Lunch", menuItems: [MealtimeItem(name: "pork", id: "123", description: "", station: "", dietaryRestrictions: KotlinMutableSet<DietaryRestriction>())]),
            MealtimeMenu(cafe: "deece", date: "24-05-2023", label: "Dinner", menuItems: [MealtimeItem(name: "pork", id: "123", description: "", station: "", dietaryRestrictions: KotlinMutableSet<DietaryRestriction>())])]
        
        return MainView(viewModel: viewModel)
    }
}
