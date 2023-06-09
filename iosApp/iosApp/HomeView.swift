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
    @State var itemsList: [MealtimeItem] = [MealtimeItem]()
    
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
                Spacer()
                Text("Menu").bold()
            }.padding().background(Color("purple_700"))
            
            
            HStack (alignment: .center, spacing: 20) {
                Spacer(minLength: 0)
                ForEach(viewModel.menus, id: \.self) { data in
                    Button {
                        itemsList = data.menuItems
                    } label: {
                        Text(String(data.label)).foregroundColor(Color.black).bold().lineLimit(1).font(.system(size: 15))
                    }.onAppear {
                        Task {
                            itemsList = viewModel.menus.first?.menuItems ?? [MealtimeItem]()
                        }
                    }
                }
                Spacer(minLength: 0)
            }
            .padding()
            .background(LinearGradient(colors: [Color("purple_700"), .mint.opacity(0.5)], startPoint: .top, endPoint: .bottom))
            
            Spacer(minLength: 0)
            ScrollView {
                ItemsView(itemsList: $itemsList)
            }
            Spacer()
        }
    }
}

struct ItemsView: View {
    
    @Binding var itemsList: [MealtimeItem]
    
    var body: some View {
        VStack(spacing: 0) {
            ForEach($itemsList, id: \.self) { $item in
                VStack(alignment: .leading) {
                    Text(item.name)
                        .font(.headline)
                    Spacer()
                    HStack {
                        Text(item.description)
                        Spacer()
                        Text(item.station)
                    }
                    .font(.caption)
                }
                .padding()
                .foregroundColor(.gray)
            }
        }
    }
}
