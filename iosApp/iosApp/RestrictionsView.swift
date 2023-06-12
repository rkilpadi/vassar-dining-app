//
//  RestrictionsView.swift
//  iosApp
//
//  Created by Lawrence Wang on 6/9/23.
//

import SwiftUI
import shared

struct RestrictionsView: View {
    
    @Binding var presentSideMenu: Bool
    @StateObject var user: User
    @StateObject var viewModel: MenuViewModel
    
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
                Text("Dietary Restrictions").bold()
                Spacer()
            }
            .padding()
            .background(Color("purple_700"))
            
            ScrollView {
                VStack {
                    ForEach(user.dietRes.indices, id: \.self) { index in
                        let (res, _) = user.dietRes[index]
                        
                        HStack {
                            Image(res.iconId)
                            Toggle(res.label, isOn: $user.dietRes[index].1)
                                .toggleStyle(SwitchToggleStyle(tint: Color("purple_700")))
                                .onChange(of: user.dietRes[index].1) { _ in
                                    user.saveToStorage()
                                }
                        }
                        .padding(.top, 15)
                        .padding(.horizontal)
                    }
                }
            }
            Spacer()
        }
    }
}
