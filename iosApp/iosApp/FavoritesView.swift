//
//  FavoritesView.swift
//  iosApp
//
//  Created by Lawrence Wang on 6/9/23.
//

import SwiftUI
import shared

struct FavoritesView: View {
    
    @Binding var presentSideMenu: Bool
    @StateObject var viewModel: MenuViewModel
    @StateObject var user: User
    @State var showRes: Bool = false
    @State var showFav: Bool = false
    
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
                Text("Favorites").bold()
                Spacer()
            }.padding().background(Color("purple_700"))
            
            ScrollView {
                ItemsView(items: user.favItems, user: user, showRes: $showRes, showFav: $showFav)
                    .padding()
            }
            Spacer()
        }
    }
}
